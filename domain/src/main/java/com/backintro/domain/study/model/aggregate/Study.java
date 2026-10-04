package com.backintro.domain.study.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.study.event.StudyRegisteredEvent;
import com.backintro.domain.study.event.StudyUpdatedEvent;
import com.backintro.domain.study.model.valueobject.StudyId;

public class Study extends AggregateRoot {

    private final StudyId id;
    private String name;

    private Study(
            StudyId id,
            String name
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
    }

    public static Study register(String name) {
        StudyId id = StudyId.generate();
        Study study = new Study(id, name);

        study.recordEvent(
                new StudyRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return study;
    }

    public static Study restore(
            StudyId id,
            String name
    ) {
        return new Study(id, name);
    }

    public void update(String name) {
        this.name = Objects.requireNonNull(name, "name must not be null");

        recordEvent(
                new StudyUpdatedEvent(
                        this.id,
                        this.name,
                        LocalDateTime.now()
                )
        );
    }

    public StudyId id() {
        return id;
    }

    public String name() {
        return name;
    }
}
