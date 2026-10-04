package com.backintro.domain.consenttype.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.consenttype.event.ConsentTypeRegisteredEvent;
import com.backintro.domain.consenttype.event.ConsentTypeUpdatedEvent;
import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;
import com.backintro.domain.common.model.AggregateRoot;

public class ConsentType extends AggregateRoot {

    private final ConsentTypeId id;
    private String name;
    private String code;
    private boolean active;
    private String description;

    private ConsentType(
            ConsentTypeId id,
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

    public static ConsentType register(
            String name,
            String code,
            String description
    ) {
        ConsentTypeId id = ConsentTypeId.generate();
        ConsentType consentType = new ConsentType(
                id,
                name,
                code,
                true,
                description
        );

        consentType.recordEvent(
                new ConsentTypeRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return consentType;
    }

    public static ConsentType restore(
            ConsentTypeId id,
            String name,
            String code,
            boolean active,
            String description
    ) {
        return new ConsentType(id, name, code, active, description);
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
                new ConsentTypeUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        this.description,
                        LocalDateTime.now()
                )
        );
    }

    public ConsentTypeId id() {
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
