package com.backintro.infrastructure.treatmentstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.treatmentstatus.usecase.DeleteTreatmentStatusUseCase;
import com.backintro.application.treatmentstatus.usecase.GetTreatmentStatusByIdUseCase;
import com.backintro.application.treatmentstatus.usecase.ListTreatmentStatusUseCase;
import com.backintro.application.treatmentstatus.usecase.RegisterTreatmentStatusUseCase;
import com.backintro.application.treatmentstatus.usecase.UpdateTreatmentStatusUseCase;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

@Configuration
public class TreatmentStatusBeanConfiguration {

    @Bean
    RegisterTreatmentStatusUseCase registerTreatmentStatusUseCase(
            TreatmentStatusRepository repository
    ) {
        return new RegisterTreatmentStatusUseCase(repository);
    }

    @Bean
    GetTreatmentStatusByIdUseCase getTreatmentStatusByIdUseCase(
            TreatmentStatusRepository repository
    ) {
        return new GetTreatmentStatusByIdUseCase(repository);
    }

    @Bean
    ListTreatmentStatusUseCase listTreatmentStatusUseCase(
            TreatmentStatusRepository repository
    ) {
        return new ListTreatmentStatusUseCase(repository);
    }

    @Bean
    UpdateTreatmentStatusUseCase updateTreatmentStatusUseCase(
            TreatmentStatusRepository repository
    ) {
        return new UpdateTreatmentStatusUseCase(repository);
    }

    @Bean
    DeleteTreatmentStatusUseCase deleteTreatmentStatusUseCase(
            TreatmentStatusRepository repository
    ) {
        return new DeleteTreatmentStatusUseCase(repository);
    }
}
