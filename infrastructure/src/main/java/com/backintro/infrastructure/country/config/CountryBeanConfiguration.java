package com.backintro.infrastructure.country.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.country.usecase.DeleteCountryUseCase;
import com.backintro.application.country.usecase.GetCountryByIdUseCase;
import com.backintro.application.country.usecase.ListCountryUseCase;
import com.backintro.application.country.usecase.RegisterCountryUseCase;
import com.backintro.application.country.usecase.UpdateCountryUseCase;
import com.backintro.domain.country.port.repository.CountryRepository;

@Configuration
public class CountryBeanConfiguration {

    @Bean
    RegisterCountryUseCase registerCountryUseCase(CountryRepository repository) {
        return new RegisterCountryUseCase(repository);
    }

    @Bean
    GetCountryByIdUseCase getCountryByIdUseCase(CountryRepository repository) {
        return new GetCountryByIdUseCase(repository);
    }

    @Bean
    ListCountryUseCase listCountryUseCase(CountryRepository repository) {
        return new ListCountryUseCase(repository);
    }

    @Bean
    UpdateCountryUseCase updateCountryUseCase(CountryRepository repository) {
        return new UpdateCountryUseCase(repository);
    }

    @Bean
    DeleteCountryUseCase deleteCountryUseCase(CountryRepository repository) {
        return new DeleteCountryUseCase(repository);
    }
}
