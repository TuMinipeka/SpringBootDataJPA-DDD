package com.backintro.infrastructure.country.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;

public interface CountryJpaRepository
        extends JpaRepository<CountryJpaEntity, UUID> {

    boolean existsByCodeIgnoreCase(String code);
}
