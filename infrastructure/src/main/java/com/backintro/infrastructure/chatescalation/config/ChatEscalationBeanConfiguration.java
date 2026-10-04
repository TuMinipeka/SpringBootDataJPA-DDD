package com.backintro.infrastructure.chatescalation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatescalation.usecase.DeleteChatEscalationUseCase;
import com.backintro.application.chatescalation.usecase.GetChatEscalationByIdUseCase;
import com.backintro.application.chatescalation.usecase.ListChatEscalationUseCase;
import com.backintro.application.chatescalation.usecase.RegisterChatEscalationUseCase;
import com.backintro.application.chatescalation.usecase.UpdateChatEscalationUseCase;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

@Configuration
public class ChatEscalationBeanConfiguration {

    @Bean
    RegisterChatEscalationUseCase registerChatEscalationUseCase(
            ChatEscalationRepository escalationRepository,
            ChatConversationRepository conversationRepository,
            EscalationStatusRepository statusRepository
    ) {
        return new RegisterChatEscalationUseCase(
                escalationRepository,
                conversationRepository,
                statusRepository
        );
    }

    @Bean
    GetChatEscalationByIdUseCase getChatEscalationByIdUseCase(
            ChatEscalationRepository repository
    ) {
        return new GetChatEscalationByIdUseCase(repository);
    }

    @Bean
    ListChatEscalationUseCase listChatEscalationUseCase(
            ChatEscalationRepository repository
    ) {
        return new ListChatEscalationUseCase(repository);
    }

    @Bean
    UpdateChatEscalationUseCase updateChatEscalationUseCase(
            ChatEscalationRepository escalationRepository,
            EscalationStatusRepository statusRepository
    ) {
        return new UpdateChatEscalationUseCase(
                escalationRepository,
                statusRepository
        );
    }

    @Bean
    DeleteChatEscalationUseCase deleteChatEscalationUseCase(
            ChatEscalationRepository repository
    ) {
        return new DeleteChatEscalationUseCase(repository);
    }
}
