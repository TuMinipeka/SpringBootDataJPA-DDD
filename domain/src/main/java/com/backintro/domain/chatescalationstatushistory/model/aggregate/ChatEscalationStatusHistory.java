package com.backintro.domain.chatescalationstatushistory.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryRegisteredEvent;
import com.backintro.domain.chatescalationstatushistory.event.ChatEscalationStatusHistoryUpdatedEvent;
import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class ChatEscalationStatusHistory extends AggregateRoot {

    private final ChatEscalationStatusHistoryId id;
    private final ChatEscalationId escalationId;
    private EscalationStatusId escalationStatusId;

    private ChatEscalationStatusHistory(
            ChatEscalationStatusHistoryId id,
            ChatEscalationId escalationId,
            EscalationStatusId escalationStatusId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.escalationId = Objects.requireNonNull(
                escalationId,
                "escalationId must not be null"
        );
        this.escalationStatusId = Objects.requireNonNull(
                escalationStatusId,
                "escalationStatusId must not be null"
        );
    }

    public static ChatEscalationStatusHistory register(
            ChatEscalationId escalationId,
            EscalationStatusId escalationStatusId
    ) {
        ChatEscalationStatusHistoryId id =
                ChatEscalationStatusHistoryId.generate();
        ChatEscalationStatusHistory history =
                new ChatEscalationStatusHistory(
                        id,
                        escalationId,
                        escalationStatusId
                );

        history.recordEvent(
                new ChatEscalationStatusHistoryRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return history;
    }

    public static ChatEscalationStatusHistory restore(
            ChatEscalationStatusHistoryId id,
            ChatEscalationId escalationId,
            EscalationStatusId escalationStatusId
    ) {
        return new ChatEscalationStatusHistory(
                id,
                escalationId,
                escalationStatusId
        );
    }

    public void update(EscalationStatusId escalationStatusId) {
        this.escalationStatusId = Objects.requireNonNull(
                escalationStatusId,
                "escalationStatusId must not be null"
        );

        recordEvent(
                new ChatEscalationStatusHistoryUpdatedEvent(
                        this.id,
                        this.escalationStatusId,
                        LocalDateTime.now()
                )
        );
    }

    public ChatEscalationStatusHistoryId id() {
        return id;
    }

    public ChatEscalationId escalationId() {
        return escalationId;
    }

    public EscalationStatusId escalationStatusId() {
        return escalationStatusId;
    }
}
