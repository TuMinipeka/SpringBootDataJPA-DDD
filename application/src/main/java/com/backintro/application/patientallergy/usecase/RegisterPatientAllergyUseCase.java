package com.backintro.application.patientallergy.usecase;

import com.backintro.application.patient.exception.PatientNotFoundApplicationException;
import com.backintro.application.patientallergy.command.RegisterPatientAllergyCommand;
import com.backintro.application.patientallergy.dto.PatientAllergyResponse;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.domain.patient.port.repository.PatientRepository;
import com.backintro.domain.patientallergy.model.aggregate.PatientAllergy;
import com.backintro.domain.patientallergy.port.repository.PatientAllergyRepository;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class RegisterPatientAllergyUseCase {

    private final PatientAllergyRepository allergyRepository;
    private final PatientRepository patientRepository;
    private final ProfessionalRepository professionalRepository;

    public RegisterPatientAllergyUseCase(
            PatientAllergyRepository allergyRepository,
            PatientRepository patientRepository,
            ProfessionalRepository professionalRepository
    ) {
        this.allergyRepository = allergyRepository;
        this.patientRepository = patientRepository;
        this.professionalRepository = professionalRepository;
    }

    public PatientAllergyResponse execute(
            RegisterPatientAllergyCommand command
    ) {
        patientRepository.findById(command.patientId())
                .orElseThrow(() ->
                        new PatientNotFoundApplicationException(
                                command.patientId().value().toString()
                        )
                );
        validateRecordedBy(command.recordedBy());

        PatientAllergy allergy = PatientAllergy.register(
                command.patientId(),
                command.substance(),
                command.reaction(),
                command.severity(),
                command.recordedBy()
        );

        return toResponse(allergyRepository.save(allergy));
    }

    private void validateRecordedBy(ProfessionalId recordedBy) {
        if (recordedBy != null) {
            professionalRepository.findById(recordedBy)
                    .orElseThrow(() ->
                            new ProfessionalNotFoundApplicationException(
                                    recordedBy.value().toString()
                            )
                    );
        }
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
