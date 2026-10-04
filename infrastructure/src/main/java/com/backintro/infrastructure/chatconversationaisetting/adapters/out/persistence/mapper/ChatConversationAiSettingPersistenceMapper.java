package com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.backintro.infrastructure.chatconversationaisetting.adapters.out.persistence.entity.ChatConversationAiSettingJpaEntity;

@Component
public class ChatConversationAiSettingPersistenceMapper {

    public ChatConversationAiSetting toDomain(
            ChatConversationAiSettingJpaEntity entity
    ) {
        return ChatConversationAiSetting.restore(
                new ChatConversationAiSettingId(entity.getId()),
                new ChatConversationId(entity.getConversationId()),
                entity.isAiEnabled(),
                entity.getDefaultModelId() == null
                        ? null
                        : new AiModelId(entity.getDefaultModelId())
        );
    }

    public ChatConversationAiSettingJpaEntity toNewEntity(
            ChatConversationAiSetting setting
    ) {
        return new ChatConversationAiSettingJpaEntity(
                setting.id().value(),
                setting.conversationId().value(),
                setting.aiEnabled(),
                setting.defaultModelId() == null
                        ? null
                        : setting.defaultModelId().value()
        );
    }

    public void synchronize(
            ChatConversationAiSetting setting,
            ChatConversationAiSettingJpaEntity entity
    ) {
        entity.synchronize(
                setting.aiEnabled(),
                setting.defaultModelId() == null
                        ? null
                        : setting.defaultModelId().value()
        );
    }
}
