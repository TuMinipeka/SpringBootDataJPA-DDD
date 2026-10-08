package com.backintro.infrastructure.security.adapters.out.persistence.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityRoleJpaEntity;

public interface SecurityRoleJpaRepository
        extends JpaRepository<SecurityRoleJpaEntity, UUID> {

    Optional<SecurityRoleJpaEntity> findByNameIgnoreCase(String name);

    Optional<SecurityRoleJpaEntity> findByAuthorityIgnoreCase(String authority);
}
