package com.backintro.infrastructure.security.adapters.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityRefreshTokenJpaEntity;

public interface SecurityRefreshTokenJpaRepository
        extends JpaRepository<SecurityRefreshTokenJpaEntity, UUID> {

    Optional<SecurityRefreshTokenJpaEntity> findByToken(String token);

    void deleteAllByUserId(UUID userId);
}
