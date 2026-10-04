package com.backintro.infrastructure.chatmessage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatmessage.usecase.DeleteChatMessageUseCase;
import com.backintro.application.chatmessage.usecase.GetChatMessageByIdUseCase;
import com.backintro.application.chatmessage.usecase.ListChatMessageUseCase;
import com.backintro.application.chatmessage.usecase.RegisterChatMessageUseCase;
import com.backintro.application.chatmessage.usecase.UpdateChatMessageUseCase;
import com.backintro.domain.chatconversation.port.repository.ChatConversationRepository;
import com.backintro.domain.chatmessage.port.repository.ChatMessageRepository;
import com.backintro.domain.chatparticipant.port.repository.ChatParticipantRepository;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;

@Configuration
public class ChatMessageBeanConfiguration {

    @Bean
    RegisterChatMessageUseCase registerChatMessageUseCase(
            ChatMessageRepository chatMessageRepository,
            ChatConversationRepository chatConversationRepository,
            MessageTypeRepository messageTypeRepository,
            ChatParticipantRepository chatParticipantRepository
    ) {
        return new RegisterChatMessageUseCase(
                chatMessageRepository,
                chatConversationRepository,
                messageTypeRepository,
                chatParticipantRepository
        );
    }

    @Bean
    GetChatMessageByIdUseCase getChatMessageByIdUseCase(
            ChatMessageRepository repository
    ) {
        return new GetChatMessageByIdUseCase(repository);
    }

    @Bean
    ListChatMessageUseCase listChatMessageUseCase(
            ChatMessageRepository repository
    ) {
        return new ListChatMessageUseCase(repository);
    }

    @Bean
    UpdateChatMessageUseCase updateChatMessageUseCase(
            ChatMessageRepository chatMessageRepository,
            MessageTypeRepository messageTypeRepository
    ) {
        return new UpdateChatMessageUseCase(
                chatMessageRepository,
                messageTypeRepository
        );
    }

    @Bean
    DeleteChatMessageUseCase deleteChatMessageUseCase(
            ChatMessageRepository repository
    ) {
        return new DeleteChatMessageUseCase(repository);
    }
}
