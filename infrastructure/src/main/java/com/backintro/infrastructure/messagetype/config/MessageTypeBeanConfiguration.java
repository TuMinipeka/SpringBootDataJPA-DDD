package com.backintro.infrastructure.messagetype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.messagetype.usecase.DeleteMessageTypeUseCase;
import com.backintro.application.messagetype.usecase.GetMessageTypeByIdUseCase;
import com.backintro.application.messagetype.usecase.ListMessageTypeUseCase;
import com.backintro.application.messagetype.usecase.RegisterMessageTypeUseCase;
import com.backintro.application.messagetype.usecase.UpdateMessageTypeUseCase;
import com.backintro.domain.messagetype.port.repository.MessageTypeRepository;

@Configuration
public class MessageTypeBeanConfiguration {

    @Bean
    RegisterMessageTypeUseCase registerMessageTypeUseCase(
            MessageTypeRepository repository
    ) {
        return new RegisterMessageTypeUseCase(repository);
    }

    @Bean
    GetMessageTypeByIdUseCase getMessageTypeByIdUseCase(
            MessageTypeRepository repository
    ) {
        return new GetMessageTypeByIdUseCase(repository);
    }

    @Bean
    ListMessageTypeUseCase listMessageTypeUseCase(
            MessageTypeRepository repository
    ) {
        return new ListMessageTypeUseCase(repository);
    }

    @Bean
    UpdateMessageTypeUseCase updateMessageTypeUseCase(
            MessageTypeRepository repository
    ) {
        return new UpdateMessageTypeUseCase(repository);
    }

    @Bean
    DeleteMessageTypeUseCase deleteMessageTypeUseCase(
            MessageTypeRepository repository
    ) {
        return new DeleteMessageTypeUseCase(repository);
    }
}
