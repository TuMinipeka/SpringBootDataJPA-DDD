package com.backintro.infrastructure.chatconversation.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

import com.backintro.domain.chatconversation.model.aggregate.ChatConversation;
import com.backintro.domain.conversationstatus.model.valueobject.ConversationStatusId;
import com.backintro.domain.priority.model.valueobject.PriorityId;

class ChatConversationPersistenceMapperTest {

    private final ChatConversationPersistenceMapper mapper =
            new ChatConversationPersistenceMapper();

    @Test
    void mapsChatConversationInBothDirectionsWithoutCreatingDomainEvents() {
        ChatConversation original = ChatConversation.register(
                ConversationStatusId.generate(),
                PriorityId.generate(),
                LocalDateTime.of(2026, 10, 4, 10, 30)
        );

        ChatConversation restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.conversationStatusId())
                .isEqualTo(original.conversationStatusId());
        assertThat(restored.priorityId())
                .isEqualTo(original.priorityId());
        assertThat(restored.lastMessageAt())
                .isEqualTo(original.lastMessageAt());
        assertThat(restored.closed()).isFalse();
        assertThat(restored.closedAt()).isNull();
        assertThat(restored.closedBy()).isNull();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
