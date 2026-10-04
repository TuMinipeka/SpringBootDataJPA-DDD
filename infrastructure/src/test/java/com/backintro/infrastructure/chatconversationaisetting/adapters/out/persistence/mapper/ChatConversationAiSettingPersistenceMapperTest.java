package com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;

class ChatConversationAiSettingPersistenceMapperTest {

    private final ChatConversationAiSettingPersistenceMapper mapper =
            new ChatConversationAiSettingPersistenceMapper();

    @Test
    void mapsSettingInBothDirectionsWithoutCreatingDomainEvents() {
        ChatConversationAiSetting original =
                ChatConversationAiSetting.register(
                        ChatConversationId.generate(),
                        AiModelId.generate()
                );

        ChatConversationAiSetting restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.conversationId())
                .isEqualTo(original.conversationId());
        assertThat(restored.aiEnabled()).isTrue();
        assertThat(restored.defaultModelId())
                .isEqualTo(original.defaultModelId());
        assertThat(restored.domainEvents()).isEmpty();
    }
}
