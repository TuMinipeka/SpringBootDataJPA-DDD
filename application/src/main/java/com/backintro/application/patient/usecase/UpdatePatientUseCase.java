package com.backintro.application.patient.usecase;

import com.backintro.application.patient.command.UpdatePatientCommand;
import com.backintro.application.patient.dto.PatientResponse;
import com.backintro.application.patient.exception.PatientNotFoundApplicationException;
import com.backintro.domain.patient.port.repository.PatientRepository;

public class UpdatePatientUseCase {

    private final PatientRepository patientRepository;

    public UpdatePatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientResponse execute(UpdatePatientCommand command) {
        var patient = patientRepository.findById(command.id())
                .orElseThrow(() ->
                        new PatientNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        patient.update(
                command.documentNumber(),
                command.firstName(),
                command.middleName(),
                command.lastName(),
                command.secondLastName(),
                command.birthDate(),
                command.email(),
                command.phone(),
                command.address()
        );

        var updated = patientRepository.save(patient);

        return new PatientResponse(
                updated.id().value(),
                updated.documentNumber(),
                updated.firstName(),
                updated.middleName(),
                updated.lastName(),
                updated.secondLastName(),
                updated.birthDate(),
                updated.email(),
                updated.phone(),
                updated.address()
        );
    }
}
