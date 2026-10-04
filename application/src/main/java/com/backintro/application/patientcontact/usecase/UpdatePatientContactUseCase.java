package com.backintro.application.patientcontact.usecase;

import com.backintro.application.patientcontact.command.UpdatePatientContactCommand;
import com.backintro.application.patientcontact.dto.PatientContactResponse;
import com.backintro.application.patientcontact.exception.PatientContactNotFoundApplicationException;
import com.backintro.domain.patientcontact.port.repository.PatientContactRepository;

public class UpdatePatientContactUseCase {

    private final PatientContactRepository patientContactRepository;

    public UpdatePatientContactUseCase(
            PatientContactRepository patientContactRepository
    ) {
        this.patientContactRepository = patientContactRepository;
    }

    public PatientContactResponse execute(UpdatePatientContactCommand command) {
        var patientContact = patientContactRepository.findById(command.id())
                .orElseThrow(() ->
                        new PatientContactNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        patientContact.update(
                command.primaryContact(),
                command.emergencyContact()
        );

        var updated = patientContactRepository.save(patientContact);

        return new PatientContactResponse(
                updated.id().value(),
                updated.primaryContact(),
                updated.emergencyContact()
        );
    }
}
