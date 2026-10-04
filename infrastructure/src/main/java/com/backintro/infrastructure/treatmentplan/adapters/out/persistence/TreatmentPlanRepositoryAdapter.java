package com.backintro.infrastructure.treatmentplan.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import com.backintro.infrastructure.treatmentplan.adapters.out.persistence.entity.TreatmentPlanJpaEntity;
import com.backintro.infrastructure.treatmentplan.adapters.out.persistence.mapper.TreatmentPlanPersistenceMapper;
import com.backintro.infrastructure.treatmentplan.adapters.out.persistence.repository.TreatmentPlanJpaRepository;

@Repository
@Transactional
public class TreatmentPlanRepositoryAdapter
        implements TreatmentPlanRepository {

    private final TreatmentPlanJpaRepository repository;
    private final TreatmentPlanPersistenceMapper mapper;

    public TreatmentPlanRepositoryAdapter(
            TreatmentPlanJpaRepository repository,
            TreatmentPlanPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentPlan save(TreatmentPlan treatmentPlan) {
        TreatmentPlanJpaEntity entity = repository
                .findById(treatmentPlan.id().value())
                .map(existing -> {
                    mapper.synchronize(treatmentPlan, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(treatmentPlan));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TreatmentPlan> findById(TreatmentPlanId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TreatmentPlan> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(TreatmentPlan treatmentPlan) {
        repository.deleteById(treatmentPlan.id().value());
    }
}
