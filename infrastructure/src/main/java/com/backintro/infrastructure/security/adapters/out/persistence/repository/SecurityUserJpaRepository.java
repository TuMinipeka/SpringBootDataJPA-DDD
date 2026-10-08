package com.backintro.infrastructure.security.adapters.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityUserJpaEntity;

public interface SecurityUserJpaRepository
        extends JpaRepository<SecurityUserJpaEntity, UUID> {

    Optional<SecurityUserJpaEntity> findByEmailIgnoreCase(String email);

    boolean existsByEmailIgnoreCase(String email);
}
