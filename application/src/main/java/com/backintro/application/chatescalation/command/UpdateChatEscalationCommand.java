package com.backintro.application.chatescalation.command;

import java.util.Objects;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public record UpdateChatEscalationCommand(
        ChatEscalationId id,
        EscalationStatusId statusId,
        boolean fromAi,
        String reason
) {

    public UpdateChatEscalationCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(statusId, "statusId must not be null");
        Objects.requireNonNull(reason, "reason must not be null");
    }
}
