package com.backintro.application.chatconversationaisetting.usecase;

import com.backintro.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.backintro.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import com.backintro.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.backintro.domain.chatconversationaisetting.model.valueobject.ChatConversationAiSettingId;
import com.backintro.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class GetChatConversationAiSettingByIdUseCase {

    private final ChatConversationAiSettingRepository settingRepository;

    public GetChatConversationAiSettingByIdUseCase(
            ChatConversationAiSettingRepository settingRepository
    ) {
        this.settingRepository = settingRepository;
    }

    public ChatConversationAiSettingResponse execute(
            ChatConversationAiSettingId id
    ) {
        var setting = settingRepository.findById(id)
                .orElseThrow(() ->
                        new ChatConversationAiSettingNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(setting);
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
