package com.backintro.domain.relationshiptype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.relationshiptype.event.RelationshipTypeRegisteredEvent;
import com.backintro.domain.relationshiptype.event.RelationshipTypeUpdatedEvent;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public class RelationshipType extends AggregateRoot {

    private final RelationshipTypeId id;
    private String description;

    private RelationshipType(
            RelationshipTypeId id,
            String description
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.description = Objects.requireNonNull(
                description,
                "description must not be null"
        );
    }

    public static RelationshipType register(String description) {
        RelationshipTypeId id = RelationshipTypeId.generate();
        RelationshipType relationshipType =
                new RelationshipType(id, description);

        relationshipType.recordEvent(
                new RelationshipTypeRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return relationshipType;
    }

    public static RelationshipType restore(
            RelationshipTypeId id,
            String description
    ) {
        return new RelationshipType(id, description);
    }

    public void update(String description) {
        this.description = Objects.requireNonNull(
                description,
                "description must not be null"
        );

        recordEvent(
                new RelationshipTypeUpdatedEvent(
                        this.id,
                        this.description,
                        LocalDateTime.now()
                )
        );
    }

    public RelationshipTypeId id() {
        return id;
    }

    public String description() {
        return description;
    }
}
