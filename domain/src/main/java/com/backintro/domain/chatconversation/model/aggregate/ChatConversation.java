package com.backintro.domain.chatconversation.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatconversation.event.ChatConversationRegisteredEvent;
import com.backintro.domain.chatconversation.event.ChatConversationUpdatedEvent;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.priority.model.valueobject.PriorityId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public class ChatConversation extends AggregateRoot {

    private final ChatConversationId id;
    private ConversationStatusId conversationStatusId;
    private PriorityId priorityId;
    private LocalDateTime lastMessageAt;
    private boolean closed;
    private LocalDateTime closedAt;
    private ProfessionalId closedBy;

    private ChatConversation(
            ChatConversationId id,
            ConversationStatusId conversationStatusId,
            PriorityId priorityId,
            LocalDateTime lastMessageAt,
            boolean closed,
            LocalDateTime closedAt,
            ProfessionalId closedBy
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationStatusId = Objects.requireNonNull(
                conversationStatusId,
                "conversationStatusId must not be null"
        );
        this.priorityId = priorityId;
        this.lastMessageAt = lastMessageAt;
        validateClosedState(closed, closedAt);
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
    }

    public static ChatConversation register(
            ConversationStatusId conversationStatusId,
            PriorityId priorityId,
            LocalDateTime lastMessageAt
    ) {
        ChatConversationId id = ChatConversationId.generate();
        ChatConversation chatConversation = new ChatConversation(
                id,
                conversationStatusId,
                priorityId,
                lastMessageAt,
                false,
                null,
                null
        );

        chatConversation.recordEvent(
                new ChatConversationRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return chatConversation;
    }

    public static ChatConversation restore(
            ChatConversationId id,
            ConversationStatusId conversationStatusId,
            PriorityId priorityId,
            LocalDateTime lastMessageAt,
            boolean closed,
            LocalDateTime closedAt,
            ProfessionalId closedBy
    ) {
        return new ChatConversation(
                id,
                conversationStatusId,
                priorityId,
                lastMessageAt,
                closed,
                closedAt,
                closedBy
        );
    }

    public void update(
            ConversationStatusId conversationStatusId,
            PriorityId priorityId,
            LocalDateTime lastMessageAt,
            boolean closed,
            LocalDateTime closedAt,
            ProfessionalId closedBy
    ) {
        this.conversationStatusId = Objects.requireNonNull(
                conversationStatusId,
                "conversationStatusId must not be null"
        );
        this.priorityId = priorityId;
        this.lastMessageAt = lastMessageAt;
        validateClosedState(closed, closedAt);
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;

        recordEvent(
                new ChatConversationUpdatedEvent(
                        this.id,
                        this.conversationStatusId,
                        this.priorityId,
                        this.lastMessageAt,
                        this.closed,
                        this.closedAt,
                        this.closedBy,
                        LocalDateTime.now()
                )
        );
    }

    private static void validateClosedState(
            boolean closed,
            LocalDateTime closedAt
    ) {
        if (!closed && closedAt != null) {
            throw new IllegalArgumentException(
                    "closedAt must be null when the conversation is open"
            );
        }
    }

    public ChatConversationId id() {
        return id;
    }

    public ConversationStatusId conversationStatusId() {
        return conversationStatusId;
    }

    public PriorityId priorityId() {
        return priorityId;
    }

    public LocalDateTime lastMessageAt() {
        return lastMessageAt;
    }

    public boolean closed() {
        return closed;
    }

    public LocalDateTime closedAt() {
        return closedAt;
    }

    public ProfessionalId closedBy() {
        return closedBy;
    }
}
