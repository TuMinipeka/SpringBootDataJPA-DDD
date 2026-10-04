package com.backintro.infrastructure.encountermodality.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.encountermodality.model.aggregate.EncounterModality;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;
import com.backintro.domain.encountermodality.port.repository.EncounterModalityRepository;
import com.backintro.infrastructure.encountermodality.adapters.out.persistence.entity.EncounterModalityJpaEntity;
import com.backintro.infrastructure.encountermodality.adapters.out.persistence.mapper.EncounterModalityPersistenceMapper;
import com.backintro.infrastructure.encountermodality.adapters.out.persistence.repository.EncounterModalityJpaRepository;

@Repository
@Transactional
public class EncounterModalityRepositoryAdapter
        implements EncounterModalityRepository {

    private final EncounterModalityJpaRepository repository;
    private final EncounterModalityPersistenceMapper mapper;

    public EncounterModalityRepositoryAdapter(
            EncounterModalityJpaRepository repository,
            EncounterModalityPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EncounterModality save(EncounterModality encounterModality) {
        EncounterModalityJpaEntity entity = repository
                .findById(encounterModality.id().value())
                .map(existing -> {
                    mapper.synchronize(encounterModality, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(encounterModality));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EncounterModality> findById(EncounterModalityId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EncounterModality> findAll() {
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
    public void delete(EncounterModality encounterModality) {
        repository.deleteById(encounterModality.id().value());
    }
}
