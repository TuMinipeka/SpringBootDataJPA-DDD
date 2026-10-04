package com.backintro.application.patient.usecase;

import com.backintro.application.patient.dto.PatientResponse;
import com.backintro.application.patient.exception.PatientNotFoundApplicationException;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patient.port.repository.PatientRepository;

public class GetPatientByIdUseCase {

    private final PatientRepository patientRepository;

    public GetPatientByIdUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientResponse execute(PatientId id) {
        var patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new PatientResponse(
                patient.id().value(),
                patient.documentNumber(),
                patient.firstName(),
                patient.middleName(),
                patient.lastName(),
                patient.secondLastName(),
                patient.birthDate(),
                patient.email(),
                patient.phone(),
                patient.address()
        );
    }
}
