package com.backintro.infrastructure.chatairun.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.chatairun.model.aggregate.ChatAiRun;
import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatmessage.model.valueobject.ChatMessageId;
import com.backintro.infrastructure.chatairun.adapters.out.persistence.entity.ChatAiRunJpaEntity;

@Component
public class ChatAiRunPersistenceMapper {

    public ChatAiRun toDomain(ChatAiRunJpaEntity entity) {
        return ChatAiRun.restore(
                new ChatAiRunId(entity.getId()),
                new ChatConversationId(entity.getConversationId()),
                entity.getMessageId() == null
                        ? null
                        : new ChatMessageId(entity.getMessageId()),
                new AiModelId(entity.getModelId()),
                new AiRunStatusId(entity.getAiRunStatusId())
        );
    }

    public ChatAiRunJpaEntity toNewEntity(ChatAiRun chatAiRun) {
        return new ChatAiRunJpaEntity(
                chatAiRun.id().value(),
                chatAiRun.conversationId().value(),
                chatAiRun.messageId() == null
                        ? null
                        : chatAiRun.messageId().value(),
                chatAiRun.modelId().value(),
                chatAiRun.aiRunStatusId().value()
        );
    }

    public void synchronize(
            ChatAiRun chatAiRun,
            ChatAiRunJpaEntity entity
    ) {
        entity.synchronize(
                chatAiRun.messageId() == null
                        ? null
                        : chatAiRun.messageId().value(),
                chatAiRun.modelId().value(),
                chatAiRun.aiRunStatusId().value()
        );
    }
}
