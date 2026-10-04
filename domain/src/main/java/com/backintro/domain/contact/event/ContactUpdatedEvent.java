package com.backintro.domain.contact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.contact.model.valueobject.ContactId;

public record ContactUpdatedEvent(
        ContactId id,
        String fullName,
        String email,
        String notes,
        CityMunicipalityId cityId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(fullName, "fullName must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
