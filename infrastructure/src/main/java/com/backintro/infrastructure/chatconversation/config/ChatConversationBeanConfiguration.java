package com.backintro.infrastructure.chatconversation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatconversation.usecase.DeleteChatConversationUseCase;
import com.backintro.application.chatconversation.usecase.GetChatConversationByIdUseCase;
import com.backintro.application.chatconversation.usecase.ListChatConversationUseCase;
import com.backintro.application.chatconversation.usecase.RegisterChatConversationUseCase;
import com.backintro.application.chatconversation.usecase.UpdateChatConversationUseCase;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.conversationstatus.port.repository.ConversationStatusRepository;
import com.backintro.domain.priority.port.repository.PriorityRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

@Configuration
public class ChatConversationBeanConfiguration {

    @Bean
    RegisterChatConversationUseCase registerChatConversationUseCase(
            ChatConversationRepository chatConversationRepository,
            ConversationStatusRepository conversationStatusRepository,
            PriorityRepository priorityRepository
    ) {
        return new RegisterChatConversationUseCase(
                chatConversationRepository,
                conversationStatusRepository,
                priorityRepository
        );
    }

    @Bean
    GetChatConversationByIdUseCase getChatConversationByIdUseCase(
            ChatConversationRepository repository
    ) {
        return new GetChatConversationByIdUseCase(repository);
    }

    @Bean
    ListChatConversationUseCase listChatConversationUseCase(
            ChatConversationRepository repository
    ) {
        return new ListChatConversationUseCase(repository);
    }

    @Bean
    UpdateChatConversationUseCase updateChatConversationUseCase(
            ChatConversationRepository chatConversationRepository,
            ConversationStatusRepository conversationStatusRepository,
            PriorityRepository priorityRepository,
            ProfessionalRepository professionalRepository
    ) {
        return new UpdateChatConversationUseCase(
                chatConversationRepository,
                conversationStatusRepository,
                priorityRepository,
                professionalRepository
        );
    }

    @Bean
    DeleteChatConversationUseCase deleteChatConversationUseCase(
            ChatConversationRepository repository
    ) {
        return new DeleteChatConversationUseCase(repository);
    }
}
