package com.backintro.infrastructure.study.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.study.model.aggregate.Study;
import com.backintro.domain.study.model.valueobject.StudyId;
import com.backintro.domain.study.port.repository.StudyRepository;
import com.backintro.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;
import com.backintro.infrastructure.study.adapters.out.persistence.mapper.StudyPersistenceMapper;
import com.backintro.infrastructure.study.adapters.out.persistence.repository.StudyJpaRepository;

@Repository
@Transactional
public class StudyRepositoryAdapter implements StudyRepository {

    private final StudyJpaRepository repository;
    private final StudyPersistenceMapper mapper;

    public StudyRepositoryAdapter(
            StudyJpaRepository repository,
            StudyPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Study save(Study study) {
        StudyJpaEntity entity = repository
                .findById(study.id().value())
                .map(existing -> {
                    mapper.synchronize(study, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(study));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Study> findById(StudyId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Study> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return repository.existsByNameIgnoreCase(name);
    }

    @Override
    public void delete(Study study) {
        repository.deleteById(study.id().value());
    }
}
