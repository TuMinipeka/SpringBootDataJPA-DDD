package com.backintro.domain.treatmentstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.treatmentstatus.event.TreatmentStatusRegisteredEvent;
import com.backintro.domain.treatmentstatus.event.TreatmentStatusUpdatedEvent;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public class TreatmentStatus extends AggregateRoot {

    private final TreatmentStatusId id;
    private String name;
    private String code;
    private boolean active;

    private TreatmentStatus(
            TreatmentStatusId id,
            String name,
            String code,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
    }

    public static TreatmentStatus register(
            String name,
            String code
    ) {
        TreatmentStatusId id = TreatmentStatusId.generate();
        TreatmentStatus treatmentStatus = new TreatmentStatus(
                id,
                name,
                code,
                true
        );

        treatmentStatus.recordEvent(
                new TreatmentStatusRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return treatmentStatus;
    }

    public static TreatmentStatus restore(
            TreatmentStatusId id,
            String name,
            String code,
            boolean active
    ) {
        return new TreatmentStatus(id, name, code, active);
    }

    public void update(
            String name,
            String code
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");

        recordEvent(
                new TreatmentStatusUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        LocalDateTime.now()
                )
        );
    }

    public TreatmentStatusId id() {
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
