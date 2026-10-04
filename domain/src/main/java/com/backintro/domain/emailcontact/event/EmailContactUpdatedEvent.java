package com.backintro.domain.emailcontact.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.emailcontact.model.valueobject.EmailContactId;

public record EmailContactUpdatedEvent(
        EmailContactId id,
        String email,
        String notes,
        LocalDateTime occurredOn
) implements DomainEvent {

    public EmailContactUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(email, "email must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
