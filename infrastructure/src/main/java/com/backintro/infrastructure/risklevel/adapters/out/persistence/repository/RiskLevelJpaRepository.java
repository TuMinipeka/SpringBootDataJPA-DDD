package com.backintro.infrastructure.risklevel.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;

public interface RiskLevelJpaRepository
        extends JpaRepository<RiskLevelJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);

    boolean existsByNameIgnoreCase(String name);
}
