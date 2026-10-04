package com.backintro.application.chatescalationstatushistory.command;

import java.util.Objects;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record RegisterChatEscalationStatusHistoryCommand(
        ChatEscalationId escalationId,
        EscalationStatusId escalationStatusId
) {

    public RegisterChatEscalationStatusHistoryCommand {
        Objects.requireNonNull(
                escalationId,
                "escalationId must not be null"
        );
        Objects.requireNonNull(
                escalationStatusId,
                "escalationStatusId must not be null"
        );
    }
}
