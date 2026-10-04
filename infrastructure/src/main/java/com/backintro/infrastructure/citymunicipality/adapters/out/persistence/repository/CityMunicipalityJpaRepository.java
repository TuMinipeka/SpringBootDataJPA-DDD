package com.backintro.infrastructure.citymunicipality.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;

public interface CityMunicipalityJpaRepository
        extends JpaRepository<CityMunicipalityJpaEntity, UUID> {

    boolean existsByRegionIdAndCodeCityIgnoreCase(
            UUID regionId,
            String codeCity
    );
}
