package com.backintro.infrastructure.riskassessment.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.riskassessment.model.aggregate.RiskAssessment;
import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.backintro.domain.riskassessment.port.repository.RiskAssessmentRepository;
import com.backintro.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;
import com.backintro.infrastructure.riskassessment.adapters.out.persistence.mapper.RiskAssessmentPersistenceMapper;
import com.backintro.infrastructure.riskassessment.adapters.out.persistence.repository.RiskAssessmentJpaRepository;

@Repository
@Transactional
public class RiskAssessmentRepositoryAdapter
        implements RiskAssessmentRepository {

    private final RiskAssessmentJpaRepository repository;
    private final RiskAssessmentPersistenceMapper mapper;

    public RiskAssessmentRepositoryAdapter(
            RiskAssessmentJpaRepository repository,
            RiskAssessmentPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RiskAssessment save(RiskAssessment riskAssessment) {
        RiskAssessmentJpaEntity entity = repository
                .findById(riskAssessment.id().value())
                .map(existing -> {
                    mapper.synchronize(riskAssessment, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(riskAssessment));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RiskAssessment> findById(RiskAssessmentId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<RiskAssessment> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(RiskAssessment riskAssessment) {
        repository.deleteById(riskAssessment.id().value());
    }
}
