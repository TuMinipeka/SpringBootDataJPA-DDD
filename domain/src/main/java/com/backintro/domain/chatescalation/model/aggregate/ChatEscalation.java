package com.backintro.domain.chatescalation.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatescalation.event.ChatEscalationRegisteredEvent;
import com.backintro.domain.chatescalation.event.ChatEscalationUpdatedEvent;
import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public class ChatEscalation extends AggregateRoot {

    private final ChatEscalationId id;
    private final ChatConversationId conversationId;
    private EscalationStatusId statusId;
    private boolean fromAi;
    private String reason;

    private ChatEscalation(
            ChatEscalationId id,
            ChatConversationId conversationId,
            EscalationStatusId statusId,
            boolean fromAi,
            String reason
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );
        this.fromAi = fromAi;
        this.reason = Objects.requireNonNull(
                reason,
                "reason must not be null"
        );
    }

    public static ChatEscalation register(
            ChatConversationId conversationId,
            EscalationStatusId statusId,
            String reason
    ) {
        ChatEscalationId id = ChatEscalationId.generate();
        ChatEscalation escalation = new ChatEscalation(
                id,
                conversationId,
                statusId,
                false,
                reason
        );

        escalation.recordEvent(
                new ChatEscalationRegisteredEvent(id, LocalDateTime.now())
        );

        return escalation;
    }

    public static ChatEscalation restore(
            ChatEscalationId id,
            ChatConversationId conversationId,
            EscalationStatusId statusId,
            boolean fromAi,
            String reason
    ) {
        return new ChatEscalation(
                id,
                conversationId,
                statusId,
                fromAi,
                reason
        );
    }

    public void update(
            EscalationStatusId statusId,
            boolean fromAi,
            String reason
    ) {
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );
        this.fromAi = fromAi;
        this.reason = Objects.requireNonNull(
                reason,
                "reason must not be null"
        );

        recordEvent(
                new ChatEscalationUpdatedEvent(
                        this.id,
                        this.statusId,
                        this.fromAi,
                        this.reason,
                        LocalDateTime.now()
                )
        );
    }

    public ChatEscalationId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public EscalationStatusId statusId() {
        return statusId;
    }

    public boolean fromAi() {
        return fromAi;
    }

    public String reason() {
        return reason;
    }
}
