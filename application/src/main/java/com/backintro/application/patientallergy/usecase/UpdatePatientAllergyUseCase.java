package com.backintro.application.patientallergy.usecase;

import com.backintro.application.patientallergy.command.UpdatePatientAllergyCommand;
import com.backintro.application.patientallergy.dto.PatientAllergyResponse;
import com.backintro.application.patientallergy.exception.PatientAllergyNotFoundApplicationException;
import com.backintro.domain.patientallergy.model.aggregate.PatientAllergy;
import com.backintro.domain.patientallergy.port.repository.PatientAllergyRepository;

public class UpdatePatientAllergyUseCase {

    private final PatientAllergyRepository allergyRepository;

    public UpdatePatientAllergyUseCase(
            PatientAllergyRepository allergyRepository
    ) {
        this.allergyRepository = allergyRepository;
    }

    public PatientAllergyResponse execute(UpdatePatientAllergyCommand command) {
        var allergy = allergyRepository.findById(command.id())
                .orElseThrow(() ->
                        new PatientAllergyNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        allergy.update(
                command.substance(),
                command.reaction(),
                command.severity(),
                command.active()
        );

        return toResponse(allergyRepository.save(allergy));
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
