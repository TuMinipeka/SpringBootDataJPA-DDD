package com.backintro.application.chatmessage.command;

import java.util.Objects;

import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;

public record UpdateChatMessageCommand(
        ChatMessageId id,
        MessageTypeId messageTypeId,
        String content,
        String metadata
) {

    public UpdateChatMessageCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                messageTypeId,
                "messageTypeId must not be null"
        );
        Objects.requireNonNull(content, "content must not be null");
    }
}
