package com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;
import com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.entity.TreatmentGoalStatusJpaEntity;
import com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.mapper.TreatmentGoalStatusPersistenceMapper;
import com.backintro.infrastructure.treatmentgoalstatus.adapters.out.persistence.repository.TreatmentGoalStatusJpaRepository;

@Repository
@Transactional
public class TreatmentGoalStatusRepositoryAdapter
        implements TreatmentGoalStatusRepository {

    private final TreatmentGoalStatusJpaRepository repository;
    private final TreatmentGoalStatusPersistenceMapper mapper;

    public TreatmentGoalStatusRepositoryAdapter(
            TreatmentGoalStatusJpaRepository repository,
            TreatmentGoalStatusPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentGoalStatus save(
            TreatmentGoalStatus treatmentGoalStatus
    ) {
        TreatmentGoalStatusJpaEntity entity = repository
                .findById(treatmentGoalStatus.id().value())
                .map(existing -> {
                    mapper.synchronize(treatmentGoalStatus, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(treatmentGoalStatus));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TreatmentGoalStatus> findById(TreatmentGoalStatusId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TreatmentGoalStatus> findAll() {
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
    public void delete(TreatmentGoalStatus treatmentGoalStatus) {
        repository.deleteById(treatmentGoalStatus.id().value());
    }
}
