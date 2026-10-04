package com.backintro.application.chatescalation.command;

import java.util.Objects;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record RegisterChatEscalationCommand(
        ChatConversationId conversationId,
        EscalationStatusId statusId,
        String reason
) {

    public RegisterChatEscalationCommand {
        Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(reason, "reason must not be null");
    }
}
