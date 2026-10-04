package com.backintro.infrastructure.professionaltype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.professionaltype.usecase.DeleteProfessionalTypeUseCase;
import com.backintro.application.professionaltype.usecase.GetProfessionalTypeByIdUseCase;
import com.backintro.application.professionaltype.usecase.ListProfessionalTypeUseCase;
import com.backintro.application.professionaltype.usecase.RegisterProfessionalTypeUseCase;
import com.backintro.application.professionaltype.usecase.UpdateProfessionalTypeUseCase;
import com.backintro.domain.professionaltype.port.repository.ProfessionalTypeRepository;

@Configuration
public class ProfessionalTypeBeanConfiguration {

    @Bean
    RegisterProfessionalTypeUseCase registerProfessionalTypeUseCase(
            ProfessionalTypeRepository repository
    ) {
        return new RegisterProfessionalTypeUseCase(repository);
    }

    @Bean
    GetProfessionalTypeByIdUseCase getProfessionalTypeByIdUseCase(
            ProfessionalTypeRepository repository
    ) {
        return new GetProfessionalTypeByIdUseCase(repository);
    }

    @Bean
    ListProfessionalTypeUseCase listProfessionalTypeUseCase(
            ProfessionalTypeRepository repository
    ) {
        return new ListProfessionalTypeUseCase(repository);
    }

    @Bean
    UpdateProfessionalTypeUseCase updateProfessionalTypeUseCase(
            ProfessionalTypeRepository repository
    ) {
        return new UpdateProfessionalTypeUseCase(repository);
    }

    @Bean
    DeleteProfessionalTypeUseCase deleteProfessionalTypeUseCase(
            ProfessionalTypeRepository repository
    ) {
        return new DeleteProfessionalTypeUseCase(repository);
    }
}
