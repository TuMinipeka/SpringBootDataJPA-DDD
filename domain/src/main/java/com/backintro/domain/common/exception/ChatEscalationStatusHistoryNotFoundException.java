package com.backintro.domain.common.exception;

import com.backintro.domain.chatescalationstatushistory.model.valueobject.ChatEscalationStatusHistoryId;

public class ChatEscalationStatusHistoryNotFoundException
        extends RuntimeException {

    public ChatEscalationStatusHistoryNotFoundException(
            ChatEscalationStatusHistoryId id
    ) {
        super("Chat escalation status history not found with id: " + id.value());
    }
}
