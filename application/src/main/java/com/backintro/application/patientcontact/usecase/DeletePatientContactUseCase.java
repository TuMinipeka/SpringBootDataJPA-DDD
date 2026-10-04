package com.backintro.application.patientcontact.usecase;

import java.time.LocalDateTime;

import com.backintro.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import com.backintro.domain.patientcontact.event.PatientContactDeletedEvent;
import com.backintro.domain.patientcontact.model.valueobject.PatientContactId;
import com.backintro.domain.patientcontact.port.repository.PatientContactRepository;

public class DeletePatientContactUseCase {

    private final PatientContactRepository patientContactRepository;

    public DeletePatientContactUseCase(
            PatientContactRepository patientContactRepository
    ) {
        this.patientContactRepository = patientContactRepository;
    }

    public PatientContactDeletedEvent execute(PatientContactId id) {
        var patientContact = patientContactRepository.findById(id)
                .orElseThrow(() ->
                        new PatientContactNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        patientContactRepository.delete(patientContact);

        return new PatientContactDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
