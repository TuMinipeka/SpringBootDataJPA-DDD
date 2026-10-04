package com.backintro.application.chatconversation.command;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.priority.model.valueobject.PriorityId;

public record RegisterChatConversationCommand(
        ConversationStatusId conversationStatusId,
        PriorityId priorityId,
        LocalDateTime lastMessageAt
) {

    public RegisterChatConversationCommand {
        Objects.requireNonNull(
                conversationStatusId,
                "conversationStatusId must not be null"
        );
    }
}
