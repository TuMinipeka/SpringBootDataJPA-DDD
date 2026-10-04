package com.backintro.infrastructure.chatescalationstatushistory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatescalationstatushistory.usecase.DeleteChatEscalationStatusHistoryUseCase;
import com.backintro.application.chatescalationstatushistory.usecase.GetChatEscalationStatusHistoryByIdUseCase;
import com.backintro.application.chatescalationstatushistory.usecase.ListChatEscalationStatusHistoryUseCase;
import com.backintro.application.chatescalationstatushistory.usecase.RegisterChatEscalationStatusHistoryUseCase;
import com.backintro.application.chatescalationstatushistory.usecase.UpdateChatEscalationStatusHistoryUseCase;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.backintro.domain.chatescalationstatushistory.port.repository.ChatEscalationStatusHistoryRepository;
import com.backintro.domain.escalationstatus.port.repository.EscalationStatusRepository;

@Configuration
public class ChatEscalationStatusHistoryBeanConfiguration {

    @Bean
    RegisterChatEscalationStatusHistoryUseCase
            registerChatEscalationStatusHistoryUseCase(
                    ChatEscalationStatusHistoryRepository historyRepository,
                    ChatEscalationRepository escalationRepository,
                    EscalationStatusRepository statusRepository
            ) {
        return new RegisterChatEscalationStatusHistoryUseCase(
                historyRepository,
                escalationRepository,
                statusRepository
        );
    }

    @Bean
    GetChatEscalationStatusHistoryByIdUseCase
            getChatEscalationStatusHistoryByIdUseCase(
                    ChatEscalationStatusHistoryRepository repository
            ) {
        return new GetChatEscalationStatusHistoryByIdUseCase(repository);
    }

    @Bean
    ListChatEscalationStatusHistoryUseCase
            listChatEscalationStatusHistoryUseCase(
                    ChatEscalationStatusHistoryRepository repository
            ) {
        return new ListChatEscalationStatusHistoryUseCase(repository);
    }

    @Bean
    UpdateChatEscalationStatusHistoryUseCase
            updateChatEscalationStatusHistoryUseCase(
                    ChatEscalationStatusHistoryRepository historyRepository,
                    EscalationStatusRepository statusRepository
            ) {
        return new UpdateChatEscalationStatusHistoryUseCase(
                historyRepository,
                statusRepository
        );
    }

    @Bean
    DeleteChatEscalationStatusHistoryUseCase
            deleteChatEscalationStatusHistoryUseCase(
                    ChatEscalationStatusHistoryRepository repository
            ) {
        return new DeleteChatEscalationStatusHistoryUseCase(repository);
    }
}
