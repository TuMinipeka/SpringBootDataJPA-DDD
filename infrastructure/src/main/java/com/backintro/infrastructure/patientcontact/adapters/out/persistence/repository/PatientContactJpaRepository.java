package com.backintro.infrastructure.patientcontact.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;

public interface PatientContactJpaRepository
        extends JpaRepository<PatientContactJpaEntity, UUID> {

    boolean existsByPatientIdAndContactId(
            UUID patientId,
            UUID contactId
    );
}
