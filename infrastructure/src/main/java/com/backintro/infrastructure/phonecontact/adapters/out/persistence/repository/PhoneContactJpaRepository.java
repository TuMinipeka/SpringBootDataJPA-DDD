package com.backintro.infrastructure.phonecontact.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;

public interface PhoneContactJpaRepository
        extends JpaRepository<PhoneContactJpaEntity, UUID> {

    boolean existsByContactIdAndPhone(UUID contactId, String phone);
}
