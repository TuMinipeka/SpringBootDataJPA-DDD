package com.backintro.domain.chatmessage.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatmessage.event.ChatMessageRegisteredEvent;
import com.backintro.domain.chatmessage.event.ChatMessageUpdatedEvent;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;

public class ChatMessage extends AggregateRoot {

    private final ChatMessageId id;
    private final ChatConversationId conversationId;
    private MessageTypeId messageTypeId;
    private final ChatParticipantId participantId;
    private String content;
    private String metadata;

    private ChatMessage(
            ChatMessageId id,
            ChatConversationId conversationId,
            MessageTypeId messageTypeId,
            ChatParticipantId participantId,
            String content,
            String metadata
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
        this.messageTypeId = Objects.requireNonNull(
                messageTypeId,
                "messageTypeId must not be null"
        );
        this.participantId = Objects.requireNonNull(
                participantId,
                "participantId must not be null"
        );
        this.content = Objects.requireNonNull(
                content,
                "content must not be null"
        );
        this.metadata = metadata;
    }

    public static ChatMessage register(
            ChatConversationId conversationId,
            MessageTypeId messageTypeId,
            ChatParticipantId participantId,
            String content,
            String metadata
    ) {
        ChatMessageId id = ChatMessageId.generate();
        ChatMessage chatMessage = new ChatMessage(
                id,
                conversationId,
                messageTypeId,
                participantId,
                content,
                metadata
        );

        chatMessage.recordEvent(
                new ChatMessageRegisteredEvent(id, LocalDateTime.now())
        );

        return chatMessage;
    }

    public static ChatMessage restore(
            ChatMessageId id,
            ChatConversationId conversationId,
            MessageTypeId messageTypeId,
            ChatParticipantId participantId,
            String content,
            String metadata
    ) {
        return new ChatMessage(
                id,
                conversationId,
                messageTypeId,
                participantId,
                content,
                metadata
        );
    }

    public void update(
            MessageTypeId messageTypeId,
            String content,
            String metadata
    ) {
        this.messageTypeId = Objects.requireNonNull(
                messageTypeId,
                "messageTypeId must not be null"
        );
        this.content = Objects.requireNonNull(
                content,
                "content must not be null"
        );
        this.metadata = metadata;

        recordEvent(
                new ChatMessageUpdatedEvent(
                        this.id,
                        this.messageTypeId,
                        this.content,
                        this.metadata,
                        LocalDateTime.now()
                )
        );
    }

    public ChatMessageId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public MessageTypeId messageTypeId() {
        return messageTypeId;
    }

    public ChatParticipantId participantId() {
        return participantId;
    }

    public String content() {
        return content;
    }

    public String metadata() {
        return metadata;
    }
}
