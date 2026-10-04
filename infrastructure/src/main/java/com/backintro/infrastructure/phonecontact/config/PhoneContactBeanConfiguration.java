package com.backintro.infrastructure.phonecontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.phonecontact.usecase.DeletePhoneContactUseCase;
import com.backintro.application.phonecontact.usecase.GetPhoneContactByIdUseCase;
import com.backintro.application.phonecontact.usecase.ListPhoneContactUseCase;
import com.backintro.application.phonecontact.usecase.RegisterPhoneContactUseCase;
import com.backintro.application.phonecontact.usecase.UpdatePhoneContactUseCase;
import com.backintro.domain.contact.port.repository.ContactRepository;
import com.backintro.domain.phonecontact.port.repository.PhoneContactRepository;

@Configuration
public class PhoneContactBeanConfiguration {

    @Bean
    RegisterPhoneContactUseCase registerPhoneContactUseCase(
            PhoneContactRepository phoneContactRepository,
            ContactRepository contactRepository
    ) {
        return new RegisterPhoneContactUseCase(
                phoneContactRepository,
                contactRepository
        );
    }

    @Bean
    GetPhoneContactByIdUseCase getPhoneContactByIdUseCase(
            PhoneContactRepository repository
    ) {
        return new GetPhoneContactByIdUseCase(repository);
    }

    @Bean
    ListPhoneContactUseCase listPhoneContactUseCase(
            PhoneContactRepository repository
    ) {
        return new ListPhoneContactUseCase(repository);
    }

    @Bean
    UpdatePhoneContactUseCase updatePhoneContactUseCase(
            PhoneContactRepository repository
    ) {
        return new UpdatePhoneContactUseCase(repository);
    }

    @Bean
    DeletePhoneContactUseCase deletePhoneContactUseCase(
            PhoneContactRepository repository
    ) {
        return new DeletePhoneContactUseCase(repository);
    }
}
