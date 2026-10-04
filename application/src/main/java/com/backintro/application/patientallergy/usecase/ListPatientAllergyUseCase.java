package com.backintro.application.patientallergy.usecase;

import java.util.List;

import com.backintro.application.patientallergy.dto.PatientAllergyResponse;
import com.backintro.domain.patientallergy.model.aggregate.PatientAllergy;
import com.backintro.domain.patientallergy.port.repository.PatientAllergyRepository;

public class ListPatientAllergyUseCase {

    private final PatientAllergyRepository allergyRepository;

    public ListPatientAllergyUseCase(
            PatientAllergyRepository allergyRepository
    ) {
        this.allergyRepository = allergyRepository;
    }

    public List<PatientAllergyResponse> execute() {
        return allergyRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private PatientAllergyResponse toResponse(PatientAllergy allergy) {
        return new PatientAllergyResponse(
                allergy.id().value(),
                allergy.patientId().value(),
                allergy.substance(),
                allergy.reaction(),
                allergy.severity(),
                allergy.active(),
                allergy.recordedBy() == null
                        ? null
                        : allergy.recordedBy().value()
        );
    }
}
