package com.backintro.application.chatconversationaisetting.usecase;

import java.util.List;

import com.backintro.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.backintro.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.backintro.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class ListChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository settingRepository;

    public ListChatConversationAiSettingUseCase(
            ChatConversationAiSettingRepository settingRepository
    ) {
        this.settingRepository = settingRepository;
    }

    public List<ChatConversationAiSettingResponse> execute() {
        return settingRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ChatConversationAiSettingResponse toResponse(
            ChatConversationAiSetting setting
    ) {
        return new ChatConversationAiSettingResponse(
                setting.id().value(),
                setting.conversationId().value(),
                setting.aiEnabled(),
                setting.defaultModelId() == null
                        ? null
                        : setting.defaultModelId().value()
        );
    }
}
