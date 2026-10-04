package com.backintro.application.conversationstatus.command;

import java.util.Objects;

import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;

public record UpdateConversationStatusCommand(
        ConversationStatusId id,
        String nameStatus
) {

    public UpdateConversationStatusCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameStatus, "nameStatus must not be null");
    }
}
