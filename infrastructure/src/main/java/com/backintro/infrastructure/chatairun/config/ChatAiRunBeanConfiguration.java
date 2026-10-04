package com.backintro.infrastructure.chatairun.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatairun.usecase.DeleteChatAiRunUseCase;
import com.backintro.application.chatairun.usecase.GetChatAiRunByIdUseCase;
import com.backintro.application.chatairun.usecase.ListChatAiRunUseCase;
import com.backintro.application.chatairun.usecase.RegisterChatAiRunUseCase;
import com.backintro.application.chatairun.usecase.UpdateChatAiRunUseCase;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;

@Configuration
public class ChatAiRunBeanConfiguration {

    @Bean
    RegisterChatAiRunUseCase registerChatAiRunUseCase(
            ChatAiRunRepository chatAiRunRepository,
            ChatConversationRepository conversationRepository,
            ChatMessageRepository messageRepository,
            AiModelRepository aiModelRepository,
            AiRunStatusRepository aiRunStatusRepository
    ) {
        return new RegisterChatAiRunUseCase(
                chatAiRunRepository,
                conversationRepository,
                messageRepository,
                aiModelRepository,
                aiRunStatusRepository
        );
    }

    @Bean
    GetChatAiRunByIdUseCase getChatAiRunByIdUseCase(
            ChatAiRunRepository repository
    ) {
        return new GetChatAiRunByIdUseCase(repository);
    }

    @Bean
    ListChatAiRunUseCase listChatAiRunUseCase(
            ChatAiRunRepository repository
    ) {
        return new ListChatAiRunUseCase(repository);
    }

    @Bean
    UpdateChatAiRunUseCase updateChatAiRunUseCase(
            ChatAiRunRepository chatAiRunRepository,
            ChatMessageRepository messageRepository,
            AiModelRepository aiModelRepository,
            AiRunStatusRepository aiRunStatusRepository
    ) {
        return new UpdateChatAiRunUseCase(
                chatAiRunRepository,
                messageRepository,
                aiModelRepository,
                aiRunStatusRepository
        );
    }

    @Bean
    DeleteChatAiRunUseCase deleteChatAiRunUseCase(
            ChatAiRunRepository repository
    ) {
        return new DeleteChatAiRunUseCase(repository);
    }
}
