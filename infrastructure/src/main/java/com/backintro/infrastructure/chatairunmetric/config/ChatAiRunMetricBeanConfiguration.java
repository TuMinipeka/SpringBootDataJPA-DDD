package com.backintro.infrastructure.chatairunmetric.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.chatairunmetric.usecase.DeleteChatAiRunMetricUseCase;
import com.backintro.application.chatairunmetric.usecase.GetChatAiRunMetricByIdUseCase;
import com.backintro.application.chatairunmetric.usecase.ListChatAiRunMetricUseCase;
import com.backintro.application.chatairunmetric.usecase.RegisterChatAiRunMetricUseCase;
import com.backintro.application.chatairunmetric.usecase.UpdateChatAiRunMetricUseCase;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;
import com.backintro.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

@Configuration
public class ChatAiRunMetricBeanConfiguration {

    @Bean
    RegisterChatAiRunMetricUseCase registerChatAiRunMetricUseCase(
            ChatAiRunMetricRepository metricRepository,
            ChatAiRunRepository chatAiRunRepository
    ) {
        return new RegisterChatAiRunMetricUseCase(
                metricRepository,
                chatAiRunRepository
        );
    }

    @Bean
    GetChatAiRunMetricByIdUseCase getChatAiRunMetricByIdUseCase(
            ChatAiRunMetricRepository repository
    ) {
        return new GetChatAiRunMetricByIdUseCase(repository);
    }

    @Bean
    ListChatAiRunMetricUseCase listChatAiRunMetricUseCase(
            ChatAiRunMetricRepository repository
    ) {
        return new ListChatAiRunMetricUseCase(repository);
    }

    @Bean
    UpdateChatAiRunMetricUseCase updateChatAiRunMetricUseCase(
            ChatAiRunMetricRepository repository
    ) {
        return new UpdateChatAiRunMetricUseCase(repository);
    }

    @Bean
    DeleteChatAiRunMetricUseCase deleteChatAiRunMetricUseCase(
            ChatAiRunMetricRepository repository
    ) {
        return new DeleteChatAiRunMetricUseCase(repository);
    }
}
