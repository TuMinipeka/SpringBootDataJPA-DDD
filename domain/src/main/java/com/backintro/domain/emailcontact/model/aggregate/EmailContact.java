package com.backintro.domain.emailcontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.emailcontact.event.EmailContactRegisteredEvent;
import com.backintro.domain.emailcontact.event.EmailContactUpdatedEvent;
import com.backintro.domain.emailcontact.model.valueobject.EmailContactId;

public class EmailContact extends AggregateRoot {

    private final EmailContactId id;
    private final ContactId contactId;
    private String email;
    private String notes;

    private EmailContact(
            EmailContactId id,
            ContactId contactId,
            String email,
            String notes
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.contactId = Objects.requireNonNull(
                contactId,
                "contactId must not be null"
        );
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.notes = notes;
    }

    public static EmailContact register(
            ContactId contactId,
            String email,
            String notes
    ) {
        EmailContactId id = EmailContactId.generate();
        EmailContact emailContact = new EmailContact(
                id,
                contactId,
                email,
                notes
        );

        emailContact.recordEvent(
                new EmailContactRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return emailContact;
    }

    public static EmailContact restore(
            EmailContactId id,
            ContactId contactId,
            String email,
            String notes
    ) {
        return new EmailContact(id, contactId, email, notes);
    }

    public void update(String email, String notes) {
        this.email = Objects.requireNonNull(email, "email must not be null");
        this.notes = notes;

        recordEvent(
                new EmailContactUpdatedEvent(
                        this.id,
                        this.email,
                        this.notes,
                        LocalDateTime.now()
                )
        );
    }

    public EmailContactId id() {
        return id;
    }

    public ContactId contactId() {
        return contactId;
    }

    public String email() {
        return email;
    }

    public String notes() {
        return notes;
    }
}
