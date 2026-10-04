package com.backintro.infrastructure.stateregion.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.stateregion.model.aggregate.StateRegion;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;
import com.backintro.domain.stateregion.port.repository.StateRegionRepository;
import com.backintro.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;
import com.backintro.infrastructure.stateregion.adapters.out.persistence.mapper.StateRegionPersistenceMapper;
import com.backintro.infrastructure.stateregion.adapters.out.persistence.repository.StateRegionJpaRepository;

@Repository
@Transactional
public class StateRegionRepositoryAdapter implements StateRegionRepository {

    private final StateRegionJpaRepository repository;
    private final StateRegionPersistenceMapper mapper;

    public StateRegionRepositoryAdapter(
            StateRegionJpaRepository repository,
            StateRegionPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public StateRegion save(StateRegion stateRegion) {
        StateRegionJpaEntity entity = repository.findById(stateRegion.id().value())
                .map(existing -> {
                    mapper.synchronize(stateRegion, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(stateRegion));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<StateRegion> findById(StateRegionId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StateRegion> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByCountryIdAndCodeRegion(
            CountryId countryId,
            String codeRegion
    ) {
        return repository.existsByCountryIdAndCodeRegionIgnoreCase(
                countryId.value(),
                codeRegion
        );
    }

    @Override
    public void delete(StateRegion stateRegion) {
        repository.deleteById(stateRegion.id().value());
    }
}
