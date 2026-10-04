package com.backintro.infrastructure.chatairun.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.chatairun.model.aggregate.ChatAiRun;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;

class ChatAiRunPersistenceMapperTest {

    private final ChatAiRunPersistenceMapper mapper =
            new ChatAiRunPersistenceMapper();

    @Test
    void mapsChatAiRunInBothDirectionsWithoutCreatingDomainEvents() {
        ChatAiRun original = ChatAiRun.register(
                ChatConversationId.generate(),
                ChatMessageId.generate(),
                AiModelId.generate(),
                AiRunStatusId.generate()
        );

        ChatAiRun restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.conversationId())
                .isEqualTo(original.conversationId());
        assertThat(restored.messageId()).isEqualTo(original.messageId());
        assertThat(restored.modelId()).isEqualTo(original.modelId());
        assertThat(restored.aiRunStatusId())
                .isEqualTo(original.aiRunStatusId());
        assertThat(restored.domainEvents()).isEmpty();
    }
}
