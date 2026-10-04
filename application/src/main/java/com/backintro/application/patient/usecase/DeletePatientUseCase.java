package com.backintro.application.patient.usecase;

import java.time.LocalDateTime;

import com.backintro.application.patient.exception.PatientNotFoundApplicationException;
import com.backintro.domain.patient.event.PatientDeletedEvent;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patient.port.repository.PatientRepository;

public class DeletePatientUseCase {

    private final PatientRepository patientRepository;

    public DeletePatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public PatientDeletedEvent execute(PatientId id) {
        var patient = patientRepository.findById(id)
                .orElseThrow(() ->
                        new PatientNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        patientRepository.delete(patient);

        return new PatientDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
