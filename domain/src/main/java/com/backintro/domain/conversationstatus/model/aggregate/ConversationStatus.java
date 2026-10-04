package com.backintro.domain.conversationstatus.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.conversationstatus.event.ConversationStatusRegisteredEvent;
import com.backintro.domain.conversationstatus.event.ConversationStatusUpdatedEvent;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;

public class ConversationStatus extends AggregateRoot {

    private final ConversationStatusId id;
    private String nameStatus;

    private ConversationStatus(
            ConversationStatusId id,
            String nameStatus
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.nameStatus = Objects.requireNonNull(
                nameStatus,
                "nameStatus must not be null"
        );
    }

    public static ConversationStatus register(String nameStatus) {
        ConversationStatusId id = ConversationStatusId.generate();
        ConversationStatus conversationStatus =
                new ConversationStatus(id, nameStatus);

        conversationStatus.recordEvent(
                new ConversationStatusRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return conversationStatus;
    }

    public static ConversationStatus restore(
            ConversationStatusId id,
            String nameStatus
    ) {
        return new ConversationStatus(id, nameStatus);
    }

    public void update(String nameStatus) {
        this.nameStatus = Objects.requireNonNull(
                nameStatus,
                "nameStatus must not be null"
        );

        recordEvent(
                new ConversationStatusUpdatedEvent(
                        this.id,
                        this.nameStatus,
                        LocalDateTime.now()
                )
        );
    }

    public ConversationStatusId id() {
        return id;
    }

    public String nameStatus() {
        return nameStatus;
    }
}
