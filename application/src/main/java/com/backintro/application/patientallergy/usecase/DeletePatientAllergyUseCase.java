package com.backintro.application.patientallergy.usecase;

import java.time.LocalDateTime;

import com.backintro.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import com.backintro.domain.patientallergy.event.PatientAllergyDeletedEvent;
import com.backintro.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.backintro.domain.patientallergy.port.repository.PatientAllergyRepository;

public class DeletePatientAllergyUseCase {

    private final PatientAllergyRepository allergyRepository;

    public DeletePatientAllergyUseCase(
            PatientAllergyRepository allergyRepository
    ) {
        this.allergyRepository = allergyRepository;
    }

    public PatientAllergyDeletedEvent execute(PatientAllergyId id) {
        var allergy = allergyRepository.findById(id)
                .orElseThrow(() ->
                        new PatientAllergyNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        allergyRepository.delete(allergy);

        return new PatientAllergyDeletedEvent(id, LocalDateTime.now());
    }
}
