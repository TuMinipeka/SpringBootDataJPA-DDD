package com.backintro.domain.professional.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.professional.model.aggregate.Professional;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public interface ProfessionalRepository {

    Professional save(Professional professional);

    Optional<Professional> findById(ProfessionalId id);

    List<Professional> findAll();

    boolean existsByDocumentTypeIdAndDocumentNumber(
            DocumentTypeId documentTypeId,
            String documentNumber
    );

    boolean existsByLicenseNumber(String licenseNumber);

    void delete(Professional professional);
}
