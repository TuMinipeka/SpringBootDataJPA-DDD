package com.backintro.infrastructure.emailcontact.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.emailcontact.model.aggregate.EmailContact;
import com.backintro.domain.emailcontact.model.valueobject.EmailContactId;
import com.backintro.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;

@Component
public class EmailContactPersistenceMapper {

    public EmailContact toDomain(EmailContactJpaEntity entity) {
        return EmailContact.restore(
                new EmailContactId(entity.getId()),
                new ContactId(entity.getContactId()),
                entity.getEmail(),
                entity.getNotes()
        );
    }

    public EmailContactJpaEntity toNewEntity(EmailContact emailContact) {
        return new EmailContactJpaEntity(
                emailContact.id().value(),
                emailContact.contactId().value(),
                emailContact.email(),
                emailContact.notes()
        );
    }

    public void synchronize(
            EmailContact emailContact,
            EmailContactJpaEntity entity
    ) {
        entity.synchronize(
                emailContact.email(),
                emailContact.notes()
        );
    }
}
