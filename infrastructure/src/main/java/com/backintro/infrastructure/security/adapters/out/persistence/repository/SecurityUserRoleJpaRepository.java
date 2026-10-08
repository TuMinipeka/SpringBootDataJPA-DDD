package com.backintro.infrastructure.security.adapters.out.persistence.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityUserRoleId;
import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityUserRoleJpaEntity;

public interface SecurityUserRoleJpaRepository
        extends JpaRepository<SecurityUserRoleJpaEntity, SecurityUserRoleId> {

    List<SecurityUserRoleJpaEntity> findAllByUserId(UUID userId);

    @Modifying(flushAutomatically = true)
    @Query("delete from SecurityUserRoleJpaEntity assignment where assignment.userId = :userId")
    void deleteAllByUserId(@Param("userId") UUID userId);
}
