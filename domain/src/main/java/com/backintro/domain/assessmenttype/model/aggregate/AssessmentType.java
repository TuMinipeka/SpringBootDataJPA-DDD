package com.backintro.domain.assessmenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.assessmenttype.event.AssessmentTypeRegisteredEvent;
import com.backintro.domain.assessmenttype.event.AssessmentTypeUpdatedEvent;
import com.backintro.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.backintro.domain.common.model.AggregateRoot;

public class AssessmentType extends AggregateRoot {

    private final AssessmentTypeId id;
    private String name;
    private String code;
    private boolean active;
    private String description;

    private AssessmentType(
            AssessmentTypeId id,
            String name,
            String code,
            boolean active,
            String description
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
        this.description = description;
    }

    public static AssessmentType register(
            String name,
            String code,
            String description
    ) {
        AssessmentTypeId id = AssessmentTypeId.generate();
        AssessmentType assessmentType = new AssessmentType(
                id,
                name,
                code,
                true,
                description
        );

        assessmentType.recordEvent(
                new AssessmentTypeRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return assessmentType;
    }

    public static AssessmentType restore(
            AssessmentTypeId id,
            String name,
            String code,
            boolean active,
            String description
    ) {
        return new AssessmentType(id, name, code, active, description);
    }

    public void update(
            String name,
            String code,
            String description
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.description = description;

        recordEvent(
                new AssessmentTypeUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        this.description,
                        LocalDateTime.now()
                )
        );
    }

    public AssessmentTypeId id() {
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

    public String description() {
        return description;
    }
}
