package com.backintro.infrastructure.phonecontact.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.phonecontact.model.aggregate.PhoneContact;
import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;
import com.backintro.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;

@Component
public class PhoneContactPersistenceMapper {

    public PhoneContact toDomain(PhoneContactJpaEntity entity) {
        return PhoneContact.restore(
                new PhoneContactId(entity.getId()),
                new ContactId(entity.getContactId()),
                entity.getPhone(),
                entity.getNotes()
        );
    }

    public PhoneContactJpaEntity toNewEntity(PhoneContact phoneContact) {
        return new PhoneContactJpaEntity(
                phoneContact.id().value(),
                phoneContact.contactId().value(),
                phoneContact.phone(),
                phoneContact.notes()
        );
    }

    public void synchronize(
            PhoneContact phoneContact,
            PhoneContactJpaEntity entity
    ) {
        entity.synchronize(
                phoneContact.phone(),
                phoneContact.notes()
        );
    }
}
