package com.backintro.infrastructure.encounterstatus.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.encounterstatus.model.aggregate.EncounterStatus;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;
import com.backintro.infrastructure.encounterstatus.adapters.out.persistence.entity.EncounterStatusJpaEntity;
import com.backintro.infrastructure.encounterstatus.adapters.out.persistence.mapper.EncounterStatusPersistenceMapper;
import com.backintro.infrastructure.encounterstatus.adapters.out.persistence.repository.EncounterStatusJpaRepository;

@Repository
@Transactional
public class EncounterStatusRepositoryAdapter
        implements EncounterStatusRepository {

    private final EncounterStatusJpaRepository repository;
    private final EncounterStatusPersistenceMapper mapper;

    public EncounterStatusRepositoryAdapter(
            EncounterStatusJpaRepository repository,
            EncounterStatusPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EncounterStatus save(EncounterStatus encounterStatus) {
        EncounterStatusJpaEntity entity = repository
                .findById(encounterStatus.id().value())
                .map(existing -> {
                    mapper.synchronize(encounterStatus, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(encounterStatus));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EncounterStatus> findById(EncounterStatusId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EncounterStatus> findAll() {
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
    public void delete(EncounterStatus encounterStatus) {
        repository.deleteById(encounterStatus.id().value());
    }
}
