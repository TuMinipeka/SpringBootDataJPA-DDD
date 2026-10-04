package com.backintro.domain.phonecontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;

public record PhoneContactUpdatedEvent(
        PhoneContactId id,
        String phone,
        String notes,
        LocalDateTime occurredOn
) implements DomainEvent {

    public PhoneContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(phone, "phone must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
