package com.backintro.domain.gender.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.gender.event.GenderRegisteredEvent;
import com.backintro.domain.gender.event.GenderUpdatedEvent;
import com.backintro.domain.gender.model.valueobject.GenderId;

public class Gender extends AggregateRoot {

    private final GenderId id;
    private String description;

    private Gender(
            GenderId id,
            String description
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.description = Objects.requireNonNull(
                description,
                "description must not be null"
        );
    }

    public static Gender register(String description) {
        GenderId id = GenderId.generate();
        Gender gender = new Gender(id, description);

        gender.recordEvent(
                new GenderRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return gender;
    }

    public static Gender restore(
            GenderId id,
            String description
    ) {
        return new Gender(id, description);
    }

    public void update(String description) {
        this.description = Objects.requireNonNull(
                description,
                "description must not be null"
        );

        recordEvent(
                new GenderUpdatedEvent(
                        this.id,
                        this.description,
                        LocalDateTime.now()
                )
        );
    }

    public GenderId id() {
        return id;
    }

    public String description() {
        return description;
    }
}
