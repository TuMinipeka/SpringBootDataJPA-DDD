package com.backintro.domain.chatconversationaisetting.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatconversationaisetting.event.ChatConversationAiSettingRegisteredEvent;
import com.backintro.domain.chatconversationaisetting.event.ChatConversationAiSettingUpdatedEvent;
import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.backintro.domain.common.model.AggregateRoot;

public class ChatConversationAiSetting extends AggregateRoot {

    private final ChatConversationAiSettingId id;
    private final ChatConversationId conversationId;
    private boolean aiEnabled;
    private AiModelId defaultModelId;

    private ChatConversationAiSetting(
            ChatConversationAiSettingId id,
            ChatConversationId conversationId,
            boolean aiEnabled,
            AiModelId defaultModelId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
        this.aiEnabled = aiEnabled;
        this.defaultModelId = defaultModelId;
    }

    public static ChatConversationAiSetting register(
            ChatConversationId conversationId,
            AiModelId defaultModelId
    ) {
        ChatConversationAiSettingId id =
                ChatConversationAiSettingId.generate();
        ChatConversationAiSetting setting =
                new ChatConversationAiSetting(
                        id,
                        conversationId,
                        true,
                        defaultModelId
                );

        setting.recordEvent(
                new ChatConversationAiSettingRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return setting;
    }

    public static ChatConversationAiSetting restore(
            ChatConversationAiSettingId id,
            ChatConversationId conversationId,
            boolean aiEnabled,
            AiModelId defaultModelId
    ) {
        return new ChatConversationAiSetting(
                id,
                conversationId,
                aiEnabled,
                defaultModelId
        );
    }

    public void update(
            boolean aiEnabled,
            AiModelId defaultModelId
    ) {
        this.aiEnabled = aiEnabled;
        this.defaultModelId = defaultModelId;

        recordEvent(
                new ChatConversationAiSettingUpdatedEvent(
                        this.id,
                        this.aiEnabled,
                        this.defaultModelId,
                        LocalDateTime.now()
                )
        );
    }

    public ChatConversationAiSettingId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public boolean aiEnabled() {
        return aiEnabled;
    }

    public AiModelId defaultModelId() {
        return defaultModelId;
    }
}
