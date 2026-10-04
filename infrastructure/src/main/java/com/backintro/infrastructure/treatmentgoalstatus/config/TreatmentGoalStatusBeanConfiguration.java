package com.backintro.infrastructure.treatmentgoalstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.treatmentgoalstatus.usecase.DeleteTreatmentGoalStatusUseCase;
import com.backintro.application.treatmentgoalstatus.usecase.GetTreatmentGoalStatusByIdUseCase;
import com.backintro.application.treatmentgoalstatus.usecase.ListTreatmentGoalStatusUseCase;
import com.backintro.application.treatmentgoalstatus.usecase.RegisterTreatmentGoalStatusUseCase;
import com.backintro.application.treatmentgoalstatus.usecase.UpdateTreatmentGoalStatusUseCase;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;

@Configuration
public class TreatmentGoalStatusBeanConfiguration {

    @Bean
    RegisterTreatmentGoalStatusUseCase registerTreatmentGoalStatusUseCase(
            TreatmentGoalStatusRepository repository
    ) {
        return new RegisterTreatmentGoalStatusUseCase(repository);
    }

    @Bean
    GetTreatmentGoalStatusByIdUseCase getTreatmentGoalStatusByIdUseCase(
            TreatmentGoalStatusRepository repository
    ) {
        return new GetTreatmentGoalStatusByIdUseCase(repository);
    }

    @Bean
    ListTreatmentGoalStatusUseCase listTreatmentGoalStatusUseCase(
            TreatmentGoalStatusRepository repository
    ) {
        return new ListTreatmentGoalStatusUseCase(repository);
    }

    @Bean
    UpdateTreatmentGoalStatusUseCase updateTreatmentGoalStatusUseCase(
            TreatmentGoalStatusRepository repository
    ) {
        return new UpdateTreatmentGoalStatusUseCase(repository);
    }

    @Bean
    DeleteTreatmentGoalStatusUseCase deleteTreatmentGoalStatusUseCase(
            TreatmentGoalStatusRepository repository
    ) {
        return new DeleteTreatmentGoalStatusUseCase(repository);
    }
}
