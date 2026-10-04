package com.backintro.infrastructure.encountertype.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.encountertype.model.aggregate.EncounterType;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;
import com.backintro.domain.encountertype.port.repository.EncounterTypeRepository;
import com.backintro.infrastructure.encountertype.adapters.out.persistence.entity.EncounterTypeJpaEntity;
import com.backintro.infrastructure.encountertype.adapters.out.persistence.mapper.EncounterTypePersistenceMapper;
import com.backintro.infrastructure.encountertype.adapters.out.persistence.repository.EncounterTypeJpaRepository;

@Repository
@Transactional
public class EncounterTypeRepositoryAdapter
        implements EncounterTypeRepository {

    private final EncounterTypeJpaRepository repository;
    private final EncounterTypePersistenceMapper mapper;

    public EncounterTypeRepositoryAdapter(
            EncounterTypeJpaRepository repository,
            EncounterTypePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public EncounterType save(EncounterType encounterType) {
        EncounterTypeJpaEntity entity = repository
                .findById(encounterType.id().value())
                .map(existing -> {
                    mapper.synchronize(encounterType, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(encounterType));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EncounterType> findById(EncounterTypeId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<EncounterType> findAll() {
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
    public void delete(EncounterType encounterType) {
        repository.deleteById(encounterType.id().value());
    }
}
