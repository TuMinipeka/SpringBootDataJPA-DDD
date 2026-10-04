package com.backintro.application.chatmessage.command;

import java.util.Objects;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;

public record RegisterChatMessageCommand(
        ChatConversationId conversationId,
        MessageTypeId messageTypeId,
        ChatParticipantId participantId,
        String content,
        String metadata
) {

    public RegisterChatMessageCommand {
        Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
        Objects.requireNonNull(
                messageTypeId,
                "messageTypeId must not be null"
        );
        Objects.requireNonNull(
                participantId,
                "participantId must not be null"
        );
        Objects.requireNonNull(content, "content must not be null");
    }
}
