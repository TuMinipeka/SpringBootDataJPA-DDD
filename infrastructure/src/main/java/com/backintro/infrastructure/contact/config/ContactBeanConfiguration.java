package com.backintro.infrastructure.contact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.contact.usecase.DeleteContactUseCase;
import com.backintro.application.contact.usecase.GetContactByIdUseCase;
import com.backintro.application.contact.usecase.ListContactUseCase;
import com.backintro.application.contact.usecase.RegisterContactUseCase;
import com.backintro.application.contact.usecase.UpdateContactUseCase;
import com.backintro.domain.contact.port.repository.ContactRepository;

@Configuration
public class ContactBeanConfiguration {

    @Bean
    RegisterContactUseCase registerContactUseCase(ContactRepository repository) {
        return new RegisterContactUseCase(repository);
    }

    @Bean
    GetContactByIdUseCase getContactByIdUseCase(ContactRepository repository) {
        return new GetContactByIdUseCase(repository);
    }

    @Bean
    ListContactUseCase listContactUseCase(ContactRepository repository) {
        return new ListContactUseCase(repository);
    }

    @Bean
    UpdateContactUseCase updateContactUseCase(ContactRepository repository) {
        return new UpdateContactUseCase(repository);
    }

    @Bean
    DeleteContactUseCase deleteContactUseCase(ContactRepository repository) {
        return new DeleteContactUseCase(repository);
    }
}
