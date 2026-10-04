package com.backintro.domain.escalationstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.escalationstatus.event.EscalationStatusRegisteredEvent;
import com.backintro.domain.escalationstatus.event.EscalationStatusUpdatedEvent;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class EscalationStatus extends AggregateRoot {

    private final EscalationStatusId id;
    private String nameStatus;

    private EscalationStatus(
            EscalationStatusId id,
            String nameStatus
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameStatus = Objects.requireNonNull(
                nameStatus,
                "nameStatus must not be null"
        );
    }

    public static EscalationStatus register(String nameStatus) {
        EscalationStatusId id = EscalationStatusId.generate();
        EscalationStatus escalationStatus =
                new EscalationStatus(id, nameStatus);

        escalationStatus.recordEvent(
                new EscalationStatusRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return escalationStatus;
    }

    public static EscalationStatus restore(
            EscalationStatusId id,
            String nameStatus
    ) {
        return new EscalationStatus(id, nameStatus);
    }

    public void update(String nameStatus) {
        this.nameStatus = Objects.requireNonNull(
                nameStatus,
                "nameStatus must not be null"
        );

        recordEvent(
                new EscalationStatusUpdatedEvent(
                        this.id,
                        this.nameStatus,
                        LocalDateTime.now()
                )
        );
    }

    public EscalationStatusId id() {
        return id;
    }

    public String nameStatus() {
        return nameStatus;
    }
}
