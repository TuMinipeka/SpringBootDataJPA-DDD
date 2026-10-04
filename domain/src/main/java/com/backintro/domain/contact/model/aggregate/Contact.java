package com.backintro.domain.contact.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.contact.event.ContactRegisteredEvent;
import com.backintro.domain.contact.event.ContactUpdatedEvent;
import com.backintro.domain.contact.model.valueobject.ContactId;

public class Contact extends AggregateRoot {

    private final ContactId id;
    private String fullName;
    private String email;
    private String notes;
    private CityMunicipalityId cityId;

    private Contact(
            ContactId id,
            String fullName,
            String email,
            String notes,
            CityMunicipalityId cityId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.fullName = Objects.requireNonNull(
                fullName,
                "fullName must not be null"
        );
        this.email = email;
        this.notes = notes;
        this.cityId = cityId;
    }

    public static Contact register(
            String fullName,
            String email,
            String notes,
            CityMunicipalityId cityId
    ) {
        ContactId id = ContactId.generate();
        Contact contact = new Contact(
                id,
                fullName,
                email,
                notes,
                cityId
        );

        contact.recordEvent(
                new ContactRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return contact;
    }

    public static Contact restore(
            ContactId id,
            String fullName,
            String email,
            String notes,
            CityMunicipalityId cityId
    ) {
        return new Contact(id, fullName, email, notes, cityId);
    }

    public void update(
            String fullName,
            String email,
            String notes,
            CityMunicipalityId cityId
    ) {
        this.fullName = Objects.requireNonNull(
                fullName,
                "fullName must not be null"
        );
        this.email = email;
        this.notes = notes;
        this.cityId = cityId;

        recordEvent(
                new ContactUpdatedEvent(
                        this.id,
                        this.fullName,
                        this.email,
                        this.notes,
                        this.cityId,
                        LocalDateTime.now()
                )
        );
    }

    public ContactId id() {
        return id;
    }

    public String fullName() {
        return fullName;
    }

    public String email() {
        return email;
    }

    public String notes() {
        return notes;
    }

    public CityMunicipalityId cityId() {
        return cityId;
    }
}
