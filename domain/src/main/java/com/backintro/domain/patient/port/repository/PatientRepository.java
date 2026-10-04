package com.backintro.domain.patient.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.patient.model.aggregate.Patient;
import com.backintro.domain.patient.model.valueobject.PatientId;

public interface PatientRepository {

    Patient save(Patient patient);

    Optional<Patient> findById(PatientId id);

    List<Patient> findAll();

    boolean existsByDocumentTypeIdAndDocumentNumber(
            DocumentTypeId documentTypeId,
            String documentNumber
    );

    void delete(Patient patient);
}
