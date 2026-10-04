package com.backintro.infrastructure.chatmessage.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatmessage.model.aggregate.ChatMessage;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.messagetype.model.valueobject.MessageTypeId;

class ChatMessagePersistenceMapperTest {

    private final ChatMessagePersistenceMapper mapper =
            new ChatMessagePersistenceMapper();

    @Test
    void mapsChatMessageInBothDirectionsWithoutCreatingDomainEvents() {
        ChatMessage original = ChatMessage.register(
                ChatConversationId.generate(),
                MessageTypeId.generate(),
                ChatParticipantId.generate(),
                "{\"text\":\"Hello\"}",
                "{\"source\":\"web\"}"
        );

        ChatMessage restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.conversationId())
                .isEqualTo(original.conversationId());
        assertThat(restored.messageTypeId())
                .isEqualTo(original.messageTypeId());
        assertThat(restored.participantId())
                .isEqualTo(original.participantId());
        assertThat(restored.content()).isEqualTo("{\"text\":\"Hello\"}");
        assertThat(restored.metadata()).isEqualTo("{\"source\":\"web\"}");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
