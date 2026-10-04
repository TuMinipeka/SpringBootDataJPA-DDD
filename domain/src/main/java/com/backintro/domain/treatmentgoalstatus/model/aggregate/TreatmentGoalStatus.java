package com.backintro.domain.treatmentgoalstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.treatmentgoalstatus.event.TreatmentGoalStatusRegisteredEvent;
import com.backintro.domain.treatmentgoalstatus.event.TreatmentGoalStatusUpdatedEvent;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public class TreatmentGoalStatus extends AggregateRoot {

    private final TreatmentGoalStatusId id;
    private String name;
    private String code;
    private boolean active;

    private TreatmentGoalStatus(
            TreatmentGoalStatusId id,
            String name,
            String code,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
    }

    public static TreatmentGoalStatus register(
            String name,
            String code
    ) {
        TreatmentGoalStatusId id = TreatmentGoalStatusId.generate();
        TreatmentGoalStatus treatmentGoalStatus = new TreatmentGoalStatus(
                id,
                name,
                code,
                true
        );

        treatmentGoalStatus.recordEvent(
                new TreatmentGoalStatusRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return treatmentGoalStatus;
    }

    public static TreatmentGoalStatus restore(
            TreatmentGoalStatusId id,
            String name,
            String code,
            boolean active
    ) {
        return new TreatmentGoalStatus(id, name, code, active);
    }

    public void update(
            String name,
            String code
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");

        recordEvent(
                new TreatmentGoalStatusUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        LocalDateTime.now()
                )
        );
    }

    public TreatmentGoalStatusId id() {
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
