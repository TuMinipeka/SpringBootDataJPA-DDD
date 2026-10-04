package com.backintro.infrastructure.treatmentstatus.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.treatmentstatus.model.aggregate.TreatmentStatus;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;
import com.backintro.infrastructure.treatmentstatus.adapters.out.persistence.entity.TreatmentStatusJpaEntity;
import com.backintro.infrastructure.treatmentstatus.adapters.out.persistence.mapper.TreatmentStatusPersistenceMapper;
import com.backintro.infrastructure.treatmentstatus.adapters.out.persistence.repository.TreatmentStatusJpaRepository;

@Repository
@Transactional
public class TreatmentStatusRepositoryAdapter
        implements TreatmentStatusRepository {

    private final TreatmentStatusJpaRepository repository;
    private final TreatmentStatusPersistenceMapper mapper;

    public TreatmentStatusRepositoryAdapter(
            TreatmentStatusJpaRepository repository,
            TreatmentStatusPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public TreatmentStatus save(TreatmentStatus treatmentStatus) {
        TreatmentStatusJpaEntity entity = repository
                .findById(treatmentStatus.id().value())
                .map(existing -> {
                    mapper.synchronize(treatmentStatus, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(treatmentStatus));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TreatmentStatus> findById(TreatmentStatusId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TreatmentStatus> findAll() {
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
    public void delete(TreatmentStatus treatmentStatus) {
        repository.deleteById(treatmentStatus.id().value());
    }
}
