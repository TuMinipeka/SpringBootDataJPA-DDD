package com.backintro.application.chatconversationaisetting.usecase;

import com.backintro.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.backintro.application.chatconversation.exception.ChatConversationNotFoundApplicationException;
import com.backintro.application.chatconversationaisetting.command.RegisterChatConversationAiSettingCommand;
import com.backintro.application.chatconversationaisetting.dto.ChatConversationAiSettingResponse;
import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.chatconversationaisetting.model.aggregate.ChatConversationAiSetting;
import com.backintro.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

public class RegisterChatConversationAiSettingUseCase {

    private final ChatConversationAiSettingRepository settingRepository;
    private final ChatConversationRepository conversationRepository;
    private final AiModelRepository aiModelRepository;

    public RegisterChatConversationAiSettingUseCase(
            ChatConversationAiSettingRepository settingRepository,
            ChatConversationRepository conversationRepository,
            AiModelRepository aiModelRepository
    ) {
        this.settingRepository = settingRepository;
        this.conversationRepository = conversationRepository;
        this.aiModelRepository = aiModelRepository;
    }

    public ChatConversationAiSettingResponse execute(
            RegisterChatConversationAiSettingCommand command
    ) {
        conversationRepository.findById(command.conversationId())
                .orElseThrow(() ->
                        new ChatConversationNotFoundApplicationException(
                                command.conversationId().value().toString()
                        )
                );
        validateDefaultModel(command.defaultModelId());

        ChatConversationAiSetting setting =
                ChatConversationAiSetting.register(
                        command.conversationId(),
                        command.defaultModelId()
                );

        return toResponse(settingRepository.save(setting));
    }

    private void validateDefaultModel(
            AiModelId modelId
    ) {
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
