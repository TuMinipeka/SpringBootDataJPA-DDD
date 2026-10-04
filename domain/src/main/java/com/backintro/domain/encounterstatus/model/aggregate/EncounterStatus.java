package com.backintro.domain.encounterstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.encounterstatus.event.EncounterStatusRegisteredEvent;
import com.backintro.domain.encounterstatus.event.EncounterStatusUpdatedEvent;
import com.backintro.domain.encounterstatus.model.valueobject.EncounterStatusId;

public class EncounterStatus extends AggregateRoot {

    private final EncounterStatusId id;
    private String name;
    private String code;
    private boolean active;

    private EncounterStatus(
            EncounterStatusId id,
            String name,
            String code,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
    }

    public static EncounterStatus register(
            String name,
            String code
    ) {
        EncounterStatusId id = EncounterStatusId.generate();
        EncounterStatus encounterStatus = new EncounterStatus(
                id,
                name,
                code,
                true
        );

        encounterStatus.recordEvent(
                new EncounterStatusRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return encounterStatus;
    }

    public static EncounterStatus restore(
            EncounterStatusId id,
            String name,
            String code,
            boolean active
    ) {
        return new EncounterStatus(id, name, code, active);
    }

    public void update(
            String name,
            String code
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");

        recordEvent(
                new EncounterStatusUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        LocalDateTime.now()
                )
        );
    }

    public EncounterStatusId id() {
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
