package com.backintro.application.chatescalationstatushistory.command;

import java.util.Objects;

import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record UpdateChatEscalationStatusHistoryCommand(
        ChatEscalationStatusHistoryId id,
        EscalationStatusId escalationStatusId
) {

    public UpdateChatEscalationStatusHistoryCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                escalationStatusId,
                "escalationStatusId must not be null"
        );
    }
}
