package com.backintro.infrastructure.emailcontact.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;

public interface EmailContactJpaRepository
        extends JpaRepository<EmailContactJpaEntity, UUID> {

    boolean existsByContactIdAndEmailIgnoreCase(UUID contactId, String email);
}
