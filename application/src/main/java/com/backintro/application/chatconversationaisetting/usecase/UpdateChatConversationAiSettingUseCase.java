package com.backintro.application.chatconversationaisetting.usecase;

import com.backintro.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.backintro.application.chatconversationaisetting.command.UpdateChatConversationAiSettingCommand;
import com.backintro.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.backintro.application.chatconversationaisetting.exception.ChatConversationAiSettingNotFoundApplicationException;
import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;
import com.backintro.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.backintro.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class UpdateChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository settingRepository;
    private final AiModelRepository aiModelRepository;

    public UpdateChatConversationAiSettingUseCase(
            ChatConversationAiSettingRepository settingRepository,
            AiModelRepository aiModelRepository
    ) {
        this.settingRepository = settingRepository;
        this.aiModelRepository = aiModelRepository;
    }

    public ChatConversationAiSettingResponse execute(
            UpdateChatConversationAiSettingCommand command
    ) {
        var setting = settingRepository.findById(command.id())
                .orElseThrow(() ->
                        new ChatConversationAiSettingNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        validateDefaultModel(command.defaultModelId());

        setting.update(
                command.aiEnabled(),
                command.defaultModelId()
        );

        return toResponse(settingRepository.save(setting));
    }

    private void validateDefaultModel(AiModelId modelId) {
        if (modelId != null) {
            aiModelRepository.findById(modelId)
                    .orElseThrow(() ->
                            new AiModelNotFoundApplicationException(
                                    modelId.value().toString()
                            )
                    );
        }
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
