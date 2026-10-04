package com.backintro.infrastructure.citymunicipality.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;
import com.backintro.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;
import com.backintro.infrastructure.citymunicipality.adapters.out.persistence.mapper.CityMunicipalityPersistenceMapper;
import com.backintro.infrastructure.citymunicipality.adapters.out.persistence.repository.CityMunicipalityJpaRepository;

@Repository
@Transactional
public class CityMunicipalityRepositoryAdapter
        implements CityMunicipalityRepository {

    private final CityMunicipalityJpaRepository repository;
    private final CityMunicipalityPersistenceMapper mapper;

    public CityMunicipalityRepositoryAdapter(
            CityMunicipalityJpaRepository repository,
            CityMunicipalityPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public CityMunicipality save(CityMunicipality cityMunicipality) {
        CityMunicipalityJpaEntity entity =
                repository.findById(cityMunicipality.id().value())
                        .map(existing -> {
                            mapper.synchronize(cityMunicipality, existing);
                            return existing;
                        })
                        .orElseGet(() ->
                                mapper.toNewEntity(cityMunicipality)
                        );

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CityMunicipality> findById(CityMunicipalityId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<CityMunicipality> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByRegionIdAndCodeCity(
            StateRegionId regionId,
            String codeCity
    ) {
        return repository.existsByRegionIdAndCodeCityIgnoreCase(
                regionId.value(),
                codeCity
        );
    }

    @Override
    public void delete(CityMunicipality cityMunicipality) {
        repository.deleteById(cityMunicipality.id().value());
    }
}
