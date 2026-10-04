package com.backintro.infrastructure.patientcontact.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientcontact.model.aggregate.PatientContact;
import com.backintro.domain.patientcontact.model.valueobject.PatientContactId;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.backintro.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;

@Component
public class PatientContactPersistenceMapper {

    public PatientContact toDomain(PatientContactJpaEntity entity) {
        return PatientContact.restore(
                new PatientContactId(entity.getId()),
                new ContactId(entity.getContactId()),
                new PatientId(entity.getPatientId()),
                entity.isPrimaryContact(),
                entity.isEmergencyContact(),
                new RelationshipTypeId(entity.getRelationshipTypeId())
        );
    }

    public PatientContactJpaEntity toNewEntity(PatientContact patientContact) {
        return new PatientContactJpaEntity(
                patientContact.id().value(),
                patientContact.contactId().value(),
                patientContact.patientId().value(),
                patientContact.primaryContact(),
                patientContact.emergencyContact(),
                patientContact.relationshipTypeId().value()
        );
    }

    public void synchronize(
            PatientContact patientContact,
            PatientContactJpaEntity entity
    ) {
        entity.synchronize(
                patientContact.primaryContact(),
                patientContact.emergencyContact()
        );
    }
}
