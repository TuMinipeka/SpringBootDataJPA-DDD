package com.backintro.application.chatconversation.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.priority.model.valueobject.PriorityId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public record UpdateChatConversationCommand(
        ChatConversationId id,
        ConversationStatusId conversationStatusId,
        PriorityId priorityId,
        LocalDateTime lastMessageAt,
        boolean closed,
        LocalDateTime closedAt,
        ProfessionalId closedBy
) {

    public UpdateChatConversationCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                conversationStatusId,
                "conversationStatusId must not be null"
        );
    }
}
