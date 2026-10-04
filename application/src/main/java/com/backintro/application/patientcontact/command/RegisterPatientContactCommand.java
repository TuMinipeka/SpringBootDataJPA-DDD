package com.backintro.application.patientcontact.command;

import java.util.Objects;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public record RegisterPatientContactCommand(
        ContactId contactId,
        PatientId patientId,
        boolean primaryContact,
        boolean emergencyContact,
        RelationshipTypeId relationshipTypeId
) {

    public RegisterPatientContactCommand {
        Objects.requireNonNull(contactId, "contactId must not be null");
        Objects.requireNonNull(patientId, "patientId must not be null");
        Objects.requireNonNull(
                relationshipTypeId,
                "relationshipTypeId must not be null"
        );
    }
}
