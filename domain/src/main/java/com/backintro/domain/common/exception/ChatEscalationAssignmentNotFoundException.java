package com.backintro.domain.common.exception;

import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;

public class ChatEscalationAssignmentNotFoundException extends RuntimeException {

    public ChatEscalationAssignmentNotFoundException(
            ChatEscalationAssignmentId id
    ) {
        super("Chat escalation assignment not found with id: " + id.value());
    }
}
