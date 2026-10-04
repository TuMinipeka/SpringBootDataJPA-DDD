package com.backintro.domain.professionaltype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.professionaltype.event.ProfessionalTypeRegisteredEvent;
import com.backintro.domain.professionaltype.event.ProfessionalTypeUpdatedEvent;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public class ProfessionalType extends AggregateRoot {

    private final ProfessionalTypeId id;
    private String name;

    private ProfessionalType(
            ProfessionalTypeId id,
            String name
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public static ProfessionalType register(String name) {
        ProfessionalTypeId id = ProfessionalTypeId.generate();
        ProfessionalType professionalType = new ProfessionalType(id, name);

        professionalType.recordEvent(
                new ProfessionalTypeRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return professionalType;
    }

    public static ProfessionalType restore(
            ProfessionalTypeId id,
            String name
    ) {
        return new ProfessionalType(id, name);
    }

    public void update(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");

        recordEvent(
                new ProfessionalTypeUpdatedEvent(
                        this.id,
                        this.name,
                        LocalDateTime.now()
                )
        );
    }

    public ProfessionalTypeId id() {
        return id;
    }

    public String name() {
        return name;
    }
}
