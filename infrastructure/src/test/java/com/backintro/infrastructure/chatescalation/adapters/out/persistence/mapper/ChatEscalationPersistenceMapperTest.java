package com.backintro.infrastructure.chatescalation.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.chatescalation.model.aggregate.ChatEscalation;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

class ChatEscalationPersistenceMapperTest {

    private final ChatEscalationPersistenceMapper mapper =
            new ChatEscalationPersistenceMapper();

    @Test
    void mapsEscalationInBothDirectionsWithoutCreatingDomainEvents() {
        ChatEscalation original = ChatEscalation.register(
                ChatConversationId.generate(),
                EscalationStatusId.generate(),
                "Human assistance is required"
        );

        ChatEscalation restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.conversationId())
                .isEqualTo(original.conversationId());
        assertThat(restored.statusId()).isEqualTo(original.statusId());
        assertThat(restored.fromAi()).isFalse();
        assertThat(restored.reason())
                .isEqualTo("Human assistance is required");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
