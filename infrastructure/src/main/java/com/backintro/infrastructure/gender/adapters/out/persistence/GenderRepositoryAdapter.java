package com.backintro.infrastructure.gender.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.gender.model.aggregate.Gender;
import com.backintro.domain.gender.model.valueobject.GenderId;
import com.backintro.domain.gender.port.repository.GenderRepository;
import com.backintro.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;
import com.backintro.infrastructure.gender.adapters.out.persistence.mapper.GenderPersistenceMapper;
import com.backintro.infrastructure.gender.adapters.out.persistence.repository.GenderJpaRepository;

@Repository
@Transactional
public class GenderRepositoryAdapter implements GenderRepository {

    private final GenderJpaRepository repository;
    private final GenderPersistenceMapper mapper;

    public GenderRepositoryAdapter(
            GenderJpaRepository repository,
            GenderPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Gender save(Gender gender) {
        GenderJpaEntity entity = repository.findById(gender.id().value())
                .map(existing -> {
                    mapper.synchronize(gender, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(gender));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Gender> findById(GenderId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Gender> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByDescription(String description) {
        return repository.existsByDescriptionIgnoreCase(description);
    }

    @Override
    public void delete(Gender gender) {
        repository.deleteById(gender.id().value());
    }
}
