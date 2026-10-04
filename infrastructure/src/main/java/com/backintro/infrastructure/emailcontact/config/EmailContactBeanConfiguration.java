package com.backintro.infrastructure.emailcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.emailcontact.usecase.DeleteEmailContactUseCase;
import com.backintro.application.emailcontact.usecase.GetEmailContactByIdUseCase;
import com.backintro.application.emailcontact.usecase.ListEmailContactUseCase;
import com.backintro.application.emailcontact.usecase.RegisterEmailContactUseCase;
import com.backintro.application.emailcontact.usecase.UpdateEmailContactUseCase;
import com.backintro.domain.contact.port.repository.ContactRepository;
import com.backintro.domain.emailcontact.port.repository.EmailContactRepository;

@Configuration
public class EmailContactBeanConfiguration {

    @Bean
    RegisterEmailContactUseCase registerEmailContactUseCase(
            EmailContactRepository emailContactRepository,
            ContactRepository contactRepository
    ) {
        return new RegisterEmailContactUseCase(
                emailContactRepository,
                contactRepository
        );
    }

    @Bean
    GetEmailContactByIdUseCase getEmailContactByIdUseCase(
            EmailContactRepository repository
    ) {
        return new GetEmailContactByIdUseCase(repository);
    }

    @Bean
    ListEmailContactUseCase listEmailContactUseCase(
            EmailContactRepository repository
    ) {
        return new ListEmailContactUseCase(repository);
    }

    @Bean
    UpdateEmailContactUseCase updateEmailContactUseCase(
            EmailContactRepository repository
    ) {
        return new UpdateEmailContactUseCase(repository);
    }

    @Bean
    DeleteEmailContactUseCase deleteEmailContactUseCase(
            EmailContactRepository repository
    ) {
        return new DeleteEmailContactUseCase(repository);
    }
}
