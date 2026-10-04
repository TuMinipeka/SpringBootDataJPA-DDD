package com.backintro.domain.medicationroute.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.medicationroute.event.MedicationRouteRegisteredEvent;
import com.backintro.domain.medicationroute.event.MedicationRouteUpdatedEvent;
import com.backintro.domain.medicationroute.model.valueobject.MedicationRouteId;

public class MedicationRoute extends AggregateRoot {

    private final MedicationRouteId id;
    private String name;
    private String code;
    private boolean active;

    private MedicationRoute(
            MedicationRouteId id,
            String name,
            String code,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
    }

    public static MedicationRoute register(
            String name,
            String code
    ) {
        MedicationRouteId id = MedicationRouteId.generate();
        MedicationRoute medicationRoute = new MedicationRoute(
                id,
                name,
                code,
                true
        );

        medicationRoute.recordEvent(
                new MedicationRouteRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return medicationRoute;
    }

    public static MedicationRoute restore(
            MedicationRouteId id,
            String name,
            String code,
            boolean active
    ) {
        return new MedicationRoute(id, name, code, active);
    }

    public void update(
            String name,
            String code
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");

        recordEvent(
                new MedicationRouteUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        LocalDateTime.now()
                )
        );
    }

    public MedicationRouteId id() {
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
