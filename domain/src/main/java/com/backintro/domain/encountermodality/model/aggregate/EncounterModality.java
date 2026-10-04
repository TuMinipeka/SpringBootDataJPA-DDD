package com.backintro.domain.encountermodality.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.encountermodality.event.EncounterModalityRegisteredEvent;
import com.backintro.domain.encountermodality.event.EncounterModalityUpdatedEvent;
import com.backintro.domain.encountermodality.model.valueobject.EncounterModalityId;

public class EncounterModality extends AggregateRoot {

    private final EncounterModalityId id;
    private String name;
    private String code;
    private boolean active;

    private EncounterModality(
            EncounterModalityId id,
            String name,
            String code,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
    }

    public static EncounterModality register(
            String name,
            String code
    ) {
        EncounterModalityId id = EncounterModalityId.generate();
        EncounterModality encounterModality = new EncounterModality(
                id,
                name,
                code,
                true
        );

        encounterModality.recordEvent(
                new EncounterModalityRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return encounterModality;
    }

    public static EncounterModality restore(
            EncounterModalityId id,
            String name,
            String code,
            boolean active
    ) {
        return new EncounterModality(id, name, code, active);
    }

    public void update(
            String name,
            String code
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");

        recordEvent(
                new EncounterModalityUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        LocalDateTime.now()
                )
        );
    }

    public EncounterModalityId id() {
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
