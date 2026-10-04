package com.backintro.infrastructure.consenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.consenttype.usecase.DeleteConsentTypeUseCase;
import com.backintro.application.consenttype.usecase.GetConsentTypeByIdUseCase;
import com.backintro.application.consenttype.usecase.ListConsentTypeUseCase;
import com.backintro.application.consenttype.usecase.RegisterConsentTypeUseCase;
import com.backintro.application.consenttype.usecase.UpdateConsentTypeUseCase;
import com.backintro.domain.consenttype.port.repository.ConsentTypeRepository;

@Configuration
public class ConsentTypeBeanConfiguration {

    @Bean
    RegisterConsentTypeUseCase registerConsentTypeUseCase(
            ConsentTypeRepository repository
    ) {
        return new RegisterConsentTypeUseCase(repository);
    }

    @Bean
    GetConsentTypeByIdUseCase getConsentTypeByIdUseCase(
            ConsentTypeRepository repository
    ) {
        return new GetConsentTypeByIdUseCase(repository);
    }

    @Bean
    ListConsentTypeUseCase listConsentTypeUseCase(
            ConsentTypeRepository repository
    ) {
        return new ListConsentTypeUseCase(repository);
    }

    @Bean
    UpdateConsentTypeUseCase updateConsentTypeUseCase(
            ConsentTypeRepository repository
    ) {
        return new UpdateConsentTypeUseCase(repository);
    }

    @Bean
    DeleteConsentTypeUseCase deleteConsentTypeUseCase(
            ConsentTypeRepository repository
    ) {
        return new DeleteConsentTypeUseCase(repository);
    }
}
