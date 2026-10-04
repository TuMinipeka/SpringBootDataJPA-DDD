package com.backintro.infrastructure.encounter.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.encounter.model.aggregate.Encounter;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.infrastructure.encounter.adapters.out.persistence.entity.EncounterJpaEntity;
import com.backintro.infrastructure.encounter.adapters.out.persistence.mapper.EncounterPersistenceMapper;
import com.backintro.infrastructure.encounter.adapters.out.persistence.repository.EncounterJpaRepository;

@Repository
@Transactional
public class EncounterRepositoryAdapter implements EncounterRepository {

    private final EncounterJpaRepository repository;
    private final EncounterPersistenceMapper mapper;

    public EncounterRepositoryAdapter(
            EncounterJpaRepository repository,
            EncounterPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Encounter save(Encounter encounter) {
        EncounterJpaEntity entity = repository
                .findById(encounter.id().value())
                .map(existing -> {
                    mapper.synchronize(encounter, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(encounter));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Encounter> findById(EncounterId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Encounter> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Encounter encounter) {
        repository.deleteById(encounter.id().value());
    }
}
