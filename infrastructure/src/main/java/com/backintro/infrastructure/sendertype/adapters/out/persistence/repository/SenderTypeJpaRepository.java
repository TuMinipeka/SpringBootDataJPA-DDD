package com.backintro.infrastructure.sendertype.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.sendertype.adapters.out.persistence.entity.SenderTypeJpaEntity;

public interface SenderTypeJpaRepository
        extends JpaRepository<SenderTypeJpaEntity, UUID> {

    boolean existsByNameTypeIgnoreCase(String nameType);
}
