package com.backintro.domain.common.exception;

import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;

public class ChatParticipantNotFoundException extends RuntimeException {

    public ChatParticipantNotFoundException(ChatParticipantId id) {
        super("Chat participant not found with id: " + id.value());
    }
}
