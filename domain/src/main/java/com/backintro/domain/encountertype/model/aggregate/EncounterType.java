package com.backintro.domain.encountertype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.encountertype.event.EncounterTypeRegisteredEvent;
import com.backintro.domain.encountertype.event.EncounterTypeUpdatedEvent;
import com.backintro.domain.encountertype.model.valueobject.EncounterTypeId;

public class EncounterType extends AggregateRoot {

    private final EncounterTypeId id;
    private String name;
    private String code;
    private boolean active;

    private EncounterType(
            EncounterTypeId id,
            String name,
            String code,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
    }

    public static EncounterType register(
            String name,
            String code
    ) {
        EncounterTypeId id = EncounterTypeId.generate();
        EncounterType encounterType = new EncounterType(
                id,
                name,
                code,
                true
        );

        encounterType.recordEvent(
                new EncounterTypeRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return encounterType;
    }

    public static EncounterType restore(
            EncounterTypeId id,
            String name,
            String code,
            boolean active
    ) {
        return new EncounterType(id, name, code, active);
    }

    public void update(
            String name,
            String code
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");

        recordEvent(
                new EncounterTypeUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        LocalDateTime.now()
                )
        );
    }

    public EncounterTypeId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String code() {
        return code;
    }

    public boolean active() {
        return active;
    }
}
