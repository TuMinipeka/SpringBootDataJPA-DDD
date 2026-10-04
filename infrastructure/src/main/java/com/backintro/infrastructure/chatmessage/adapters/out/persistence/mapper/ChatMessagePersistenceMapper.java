package com.backintro.infrastructure.chatmessage.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatmessage.model.aggregate.ChatMessage;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;
import com.backintro.infrastructure.chatmessage.adapters.out.persistence.entity.ChatMessageJpaEntity;

@Component
public class ChatMessagePersistenceMapper {

    public ChatMessage toDomain(ChatMessageJpaEntity entity) {
        return ChatMessage.restore(
                new ChatMessageId(entity.getId()),
                new ChatConversationId(entity.getConversationId()),
                new MessageTypeId(entity.getMessageTypeId()),
                new ChatParticipantId(entity.getParticipantId()),
                entity.getContent(),
                entity.getMetadata()
        );
    }

    public ChatMessageJpaEntity toNewEntity(ChatMessage chatMessage) {
        return new ChatMessageJpaEntity(
                chatMessage.id().value(),
                chatMessage.conversationId().value(),
                chatMessage.messageTypeId().value(),
                chatMessage.participantId().value(),
                chatMessage.content(),
                chatMessage.metadata()
        );
    }

    public void synchronize(
            ChatMessage chatMessage,
            ChatMessageJpaEntity entity
    ) {
        entity.synchronize(
                chatMessage.messageTypeId().value(),
                chatMessage.content(),
                chatMessage.metadata()
        );
    }
}
