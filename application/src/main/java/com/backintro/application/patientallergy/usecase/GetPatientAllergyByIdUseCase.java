package com.backintro.application.patientallergy.usecase;

import com.backintro.application.patientallergy.dto.PatientAllergyResponse;
import com.backintro.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import com.backintro.domain.patientallergy.model.aggregate.PatientAllergy;
import com.backintro.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.backintro.domain.patientallergy.port.repository.PatientAllergyRepository;

public class GetPatientAllergyByIdUseCase {

    private final PatientAllergyRepository allergyRepository;

    public GetPatientAllergyByIdUseCase(
            PatientAllergyRepository allergyRepository
    ) {
        this.allergyRepository = allergyRepository;
    }

    public PatientAllergyResponse execute(PatientAllergyId id) {
        var allergy = allergyRepository.findById(id)
                .orElseThrow(() ->
                        new PatientAllergyNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(allergy);
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
