package com.backintro.domain.relationshiptype.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public record RelationshipTypeDeletedEvent(
        RelationshipTypeId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public RelationshipTypeDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
