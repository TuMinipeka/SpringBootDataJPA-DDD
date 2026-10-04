package com.backintro.domain.phonecontact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.phonecontact.event.PhoneContactRegisteredEvent;
import com.backintro.domain.phonecontact.event.PhoneContactUpdatedEvent;
import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;

public class PhoneContact extends AggregateRoot {

    private final PhoneContactId id;
    private final ContactId contactId;
    private String phone;
    private String notes;

    private PhoneContact(
            PhoneContactId id,
            ContactId contactId,
            String phone,
            String notes
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.contactId = Objects.requireNonNull(
                contactId,
                "contactId must not be null"
        );
        this.phone = Objects.requireNonNull(phone, "phone must not be null");
        this.notes = notes;
    }

    public static PhoneContact register(
            ContactId contactId,
            String phone,
            String notes
    ) {
        PhoneContactId id = PhoneContactId.generate();
        PhoneContact phoneContact = new PhoneContact(
                id,
                contactId,
                phone,
                notes
        );

        phoneContact.recordEvent(
                new PhoneContactRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return phoneContact;
    }

    public static PhoneContact restore(
            PhoneContactId id,
            ContactId contactId,
            String phone,
            String notes
    ) {
        return new PhoneContact(id, contactId, phone, notes);
    }

    public void update(String phone, String notes) {
        this.phone = Objects.requireNonNull(phone, "phone must not be null");
        this.notes = notes;

        recordEvent(
                new PhoneContactUpdatedEvent(
                        this.id,
                        this.phone,
                        this.notes,
                        LocalDateTime.now()
                )
        );
    }

    public PhoneContactId id() {
        return id;
    }

    public ContactId contactId() {
        return contactId;
    }

    public String phone() {
        return phone;
    }

    public String notes() {
        return notes;
    }
}
