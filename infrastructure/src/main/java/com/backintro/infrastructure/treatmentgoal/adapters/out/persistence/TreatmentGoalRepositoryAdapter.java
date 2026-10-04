package com.backintro.infrastructure.treatmentgoal.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.treatmentgoal.model.aggregate.TreatmentGoal;
import com.backintro.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.backintro.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.entity.TreatmentGoalJpaEntity;
import com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.mapper.TreatmentGoalPersistenceMapper;
import com.backintro.infrastructure.treatmentgoal.adapters.out.persistence.repository.TreatmentGoalJpaRepository;

@Repository
@Transactional
public class TreatmentGoalRepositoryAdapter
        implements TreatmentGoalRepository {

    private final TreatmentGoalJpaRepository repository;
    private final TreatmentGoalPersistenceMapper mapper;

    public TreatmentGoalRepositoryAdapter(
            TreatmentGoalJpaRepository repository,
            TreatmentGoalPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentGoal save(TreatmentGoal treatmentGoal) {
        TreatmentGoalJpaEntity entity = repository
                .findById(treatmentGoal.id().value())
                .map(existing -> {
                    mapper.synchronize(treatmentGoal, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(treatmentGoal));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TreatmentGoal> findById(TreatmentGoalId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TreatmentGoal> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(TreatmentGoal treatmentGoal) {
        repository.deleteById(treatmentGoal.id().value());
    }
}
