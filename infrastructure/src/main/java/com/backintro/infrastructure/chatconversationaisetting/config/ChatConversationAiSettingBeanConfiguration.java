package com.backintro.infrastructure.chatconversationaisetting.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatconversationaisetting.usecase.DeleteChatConversationAiSettingUseCase;
import com.backintro.application.chatconversationaisetting.usecase.GetChatConversationAiSettingByIdUseCase;
import com.backintro.application.chatconversationaisetting.usecase.ListChatConversationAiSettingUseCase;
import com.backintro.application.chatconversationaisetting.usecase.RegisterChatConversationAiSettingUseCase;
import com.backintro.application.chatconversationaisetting.usecase.UpdateChatConversationAiSettingUseCase;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.chatconversationaisetting.port.repository.ChatConversationAiSettingRepository;

@Configuration
public class ChatConversationAiSettingBeanConfiguration {

    @Bean
    RegisterChatConversationAiSettingUseCase
            registerChatConversationAiSettingUseCase(
                    ChatConversationAiSettingRepository settingRepository,
                    ChatConversationRepository conversationRepository,
                    AiModelRepository aiModelRepository
            ) {
        return new RegisterChatConversationAiSettingUseCase(
                settingRepository,
                conversationRepository,
                aiModelRepository
        );
    }

    @Bean
    GetChatConversationAiSettingByIdUseCase
            getChatConversationAiSettingByIdUseCase(
                    ChatConversationAiSettingRepository repository
            ) {
        return new GetChatConversationAiSettingByIdUseCase(repository);
    }

    @Bean
    ListChatConversationAiSettingUseCase
            listChatConversationAiSettingUseCase(
                    ChatConversationAiSettingRepository repository
            ) {
        return new ListChatConversationAiSettingUseCase(repository);
    }

    @Bean
    UpdateChatConversationAiSettingUseCase
            updateChatConversationAiSettingUseCase(
                    ChatConversationAiSettingRepository settingRepository,
                    AiModelRepository aiModelRepository
            ) {
        return new UpdateChatConversationAiSettingUseCase(
                settingRepository,
                aiModelRepository
        );
    }

    @Bean
    DeleteChatConversationAiSettingUseCase
            deleteChatConversationAiSettingUseCase(
                    ChatConversationAiSettingRepository repository
            ) {
        return new DeleteChatConversationAiSettingUseCase(repository);
    }
}
