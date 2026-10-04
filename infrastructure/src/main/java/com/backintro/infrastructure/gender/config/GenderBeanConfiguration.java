package com.backintro.infrastructure.gender.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.gender.usecase.DeleteGenderUseCase;
import com.backintro.application.gender.usecase.GetGenderByIdUseCase;
import com.backintro.application.gender.usecase.ListGenderUseCase;
import com.backintro.application.gender.usecase.RegisterGenderUseCase;
import com.backintro.application.gender.usecase.UpdateGenderUseCase;
import com.backintro.domain.gender.port.repository.GenderRepository;

@Configuration
public class GenderBeanConfiguration {

    @Bean
    RegisterGenderUseCase registerGenderUseCase(GenderRepository repository) {
        return new RegisterGenderUseCase(repository);
    }

    @Bean
    GetGenderByIdUseCase getGenderByIdUseCase(GenderRepository repository) {
        return new GetGenderByIdUseCase(repository);
    }

    @Bean
    ListGenderUseCase listGenderUseCase(GenderRepository repository) {
        return new ListGenderUseCase(repository);
    }

    @Bean
    UpdateGenderUseCase updateGenderUseCase(GenderRepository repository) {
        return new UpdateGenderUseCase(repository);
    }

    @Bean
    DeleteGenderUseCase deleteGenderUseCase(GenderRepository repository) {
        return new DeleteGenderUseCase(repository);
    }
}
