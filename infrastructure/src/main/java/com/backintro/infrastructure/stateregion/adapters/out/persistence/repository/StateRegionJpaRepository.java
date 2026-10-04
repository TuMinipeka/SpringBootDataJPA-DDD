package com.backintro.infrastructure.stateregion.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;

public interface StateRegionJpaRepository
        extends JpaRepository<StateRegionJpaEntity, UUID> {

    boolean existsByCountryIdAndCodeRegionIgnoreCase(
            UUID countryId,
            String codeRegion
    );
}
