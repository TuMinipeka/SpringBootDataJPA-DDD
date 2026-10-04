package com.backintro.application.patientcontact.usecase;

import com.backintro.application.contact.exception.ContactNotFoundApplicationException;
import com.backintro.application.patient.exception.PatientNotFoundApplicationException;
import com.backintro.application.patientcontact.command.RegisterPatientContactCommand;
import com.backintro.application.patientcontact.dto.PatientContactResponse;
import com.backintro.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.backintro.domain.contact.port.repository.ContactRepository;
import com.backintro.domain.patient.port.repository.PatientRepository;
import com.backintro.domain.patientcontact.model.aggregate.PatientContact;
import com.backintro.domain.patientcontact.port.repository.PatientContactRepository;
import com.backintro.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class RegisterPatientContactUseCase {

    private final PatientContactRepository patientContactRepository;
    private final ContactRepository contactRepository;
    private final PatientRepository patientRepository;
    private final RelationshipTypeRepository relationshipTypeRepository;

    public RegisterPatientContactUseCase(
            PatientContactRepository patientContactRepository,
            ContactRepository contactRepository,
            PatientRepository patientRepository,
            RelationshipTypeRepository relationshipTypeRepository
    ) {
        this.patientContactRepository = patientContactRepository;
        this.contactRepository = contactRepository;
        this.patientRepository = patientRepository;
        this.relationshipTypeRepository = relationshipTypeRepository;
    }

    public PatientContactResponse execute(
            RegisterPatientContactCommand command
    ) {
        contactRepository.findById(command.contactId())
                .orElseThrow(() ->
                        new ContactNotFoundApplicationException(
                                command.contactId().value().toString()
                        )
                );
        patientRepository.findById(command.patientId())
                .orElseThrow(() ->
                        new PatientNotFoundApplicationException(
                                command.patientId().value().toString()
                        )
                );
        relationshipTypeRepository.findById(command.relationshipTypeId())
                .orElseThrow(() ->
                        new RelationshipTypeNotFoundApplicationException(
                                command.relationshipTypeId().value().toString()
                        )
                );

        PatientContact patientContact = PatientContact.register(
                command.contactId(),
                command.patientId(),
                command.primaryContact(),
                command.emergencyContact(),
                command.relationshipTypeId()
        );

        PatientContact saved = patientContactRepository.save(patientContact);

        return toResponse(saved);
    }

    private PatientContactResponse toResponse(PatientContact patientContact) {
        return new PatientContactResponse(
                patientContact.id().value(),
                patientContact.primaryContact(),
                patientContact.emergencyContact()
        );
    }
}
