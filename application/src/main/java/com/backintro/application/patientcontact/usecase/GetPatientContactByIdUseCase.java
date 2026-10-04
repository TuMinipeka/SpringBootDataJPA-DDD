package com.backintro.application.patientcontact.usecase;

import com.backintro.application.patientcontact.dto.PatientContactResponse;
import com.backintro.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import com.backintro.domain.patientcontact.model.valueobject.PatientContactId;
import com.backintro.domain.patientcontact.port.repository.PatientContactRepository;

public class GetPatientContactByIdUseCase {

    private final PatientContactRepository patientContactRepository;

    public GetPatientContactByIdUseCase(
            PatientContactRepository patientContactRepository
    ) {
        this.patientContactRepository = patientContactRepository;
    }

    public PatientContactResponse execute(PatientContactId id) {
        var patientContact = patientContactRepository.findById(id)
                .orElseThrow(() ->
                        new PatientContactNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new PatientContactResponse(
                patientContact.id().value(),
                patientContact.primaryContact(),
                patientContact.emergencyContact()
        );
    }
}
