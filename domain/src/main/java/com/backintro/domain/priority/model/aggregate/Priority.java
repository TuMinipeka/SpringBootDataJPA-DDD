package com.backintro.domain.priority.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.priority.event.PriorityRegisteredEvent;
import com.backintro.domain.priority.event.PriorityUpdatedEvent;
import com.backintro.domain.priority.model.valueobject.PriorityId;

public class Priority extends AggregateRoot {

    private final PriorityId id;
    private String namePriority;

    private Priority(
            PriorityId id,
            String namePriority
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.namePriority = Objects.requireNonNull(
                namePriority,
                "namePriority must not be null"
        );
    }

    public static Priority register(String namePriority) {
        PriorityId id = PriorityId.generate();
        Priority priority =
                new Priority(id, namePriority);

        priority.recordEvent(
                new PriorityRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return priority;
    }

    public static Priority restore(
            PriorityId id,
            String namePriority
    ) {
        return new Priority(id, namePriority);
    }

    public void update(String namePriority) {
        this.namePriority = Objects.requireNonNull(
                namePriority,
                "namePriority must not be null"
        );

        recordEvent(
                new PriorityUpdatedEvent(
                        this.id,
                        this.namePriority,
                        LocalDateTime.now()
                )
        );
    }

    public PriorityId id() {
        return id;
    }

    public String namePriority() {
        return namePriority;
    }
}
