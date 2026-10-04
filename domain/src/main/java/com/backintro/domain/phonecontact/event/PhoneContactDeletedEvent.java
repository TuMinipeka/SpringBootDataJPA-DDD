package com.backintro.domain.phonecontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.phonecontact.model.valueobject.PhoneContactId;

public record PhoneContactDeletedEvent(
        PhoneContactId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public PhoneContactDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
