package com.backintro.infrastructure.assessmenttype.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.assessmenttype.model.aggregate.AssessmentType;
import com.backintro.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.backintro.domain.assessmenttype.port.repository.AssessmentTypeRepository;
import com.backintro.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;
import com.backintro.infrastructure.assessmenttype.adapters.out.persistence.mapper.AssessmentTypePersistenceMapper;
import com.backintro.infrastructure.assessmenttype.adapters.out.persistence.repository.AssessmentTypeJpaRepository;

@Repository
@Transactional
public class AssessmentTypeRepositoryAdapter
        implements AssessmentTypeRepository {

    private final AssessmentTypeJpaRepository repository;
    private final AssessmentTypePersistenceMapper mapper;

    public AssessmentTypeRepositoryAdapter(
            AssessmentTypeJpaRepository repository,
            AssessmentTypePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AssessmentType save(AssessmentType assessmentType) {
        AssessmentTypeJpaEntity entity = repository
                .findById(assessmentType.id().value())
                .map(existing -> {
                    mapper.synchronize(assessmentType, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(assessmentType));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AssessmentType> findById(AssessmentTypeId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AssessmentType> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCode(String code) {
        return repository.existsByCodeIgnoreCase(code);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByName(String name) {
        return repository.existsByNameIgnoreCase(name);
    }

    @Override
    public void delete(AssessmentType assessmentType) {
        repository.deleteById(assessmentType.id().value());
    }
}
