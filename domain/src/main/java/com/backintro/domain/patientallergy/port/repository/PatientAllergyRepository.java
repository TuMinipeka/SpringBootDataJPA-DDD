package com.backintro.domain.patientallergy.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.patientallergy.model.aggregate.PatientAllergy;
import com.backintro.domain.patientallergy.model.valueobject.PatientAllergyId;

public interface PatientAllergyRepository {

    PatientAllergy save(PatientAllergy allergy);

    Optional<PatientAllergy> findById(PatientAllergyId id);

    List<PatientAllergy> findAll();

    void delete(PatientAllergy allergy);
}
