package com.backintro.infrastructure.professional.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;

public interface ProfessionalJpaRepository
        extends JpaRepository<ProfessionalJpaEntity, UUID> {

    boolean existsByDocumentTypeIdAndDocumentNumberIgnoreCase(
            UUID documentTypeId,
            String documentNumber
    );

    boolean existsByLicenseNumberIgnoreCase(String licenseNumber);
}
