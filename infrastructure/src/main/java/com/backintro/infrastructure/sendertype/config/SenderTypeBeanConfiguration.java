package com.backintro.infrastructure.sendertype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.sendertype.usecase.DeleteSenderTypeUseCase;
import com.backintro.application.sendertype.usecase.GetSenderTypeByIdUseCase;
import com.backintro.application.sendertype.usecase.ListSenderTypeUseCase;
import com.backintro.application.sendertype.usecase.RegisterSenderTypeUseCase;
import com.backintro.application.sendertype.usecase.UpdateSenderTypeUseCase;
import com.backintro.domain.sendertype.port.repository.SenderTypeRepository;

@Configuration
public class SenderTypeBeanConfiguration {

    @Bean
    RegisterSenderTypeUseCase registerSenderTypeUseCase(
            SenderTypeRepository repository
    ) {
        return new RegisterSenderTypeUseCase(repository);
    }

    @Bean
    GetSenderTypeByIdUseCase getSenderTypeByIdUseCase(
            SenderTypeRepository repository
    ) {
        return new GetSenderTypeByIdUseCase(repository);
    }

    @Bean
    ListSenderTypeUseCase listSenderTypeUseCase(
            SenderTypeRepository repository
    ) {
        return new ListSenderTypeUseCase(repository);
    }

    @Bean
    UpdateSenderTypeUseCase updateSenderTypeUseCase(
            SenderTypeRepository repository
    ) {
        return new UpdateSenderTypeUseCase(repository);
    }

    @Bean
    DeleteSenderTypeUseCase deleteSenderTypeUseCase(
            SenderTypeRepository repository
    ) {
        return new DeleteSenderTypeUseCase(repository);
    }
}
