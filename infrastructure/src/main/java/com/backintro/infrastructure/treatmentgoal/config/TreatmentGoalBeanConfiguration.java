package com.backintro.infrastructure.treatmentgoal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.treatmentgoal.usecase.DeleteTreatmentGoalUseCase;
import com.backintro.application.treatmentgoal.usecase.GetTreatmentGoalByIdUseCase;
import com.backintro.application.treatmentgoal.usecase.ListTreatmentGoalUseCase;
import com.backintro.application.treatmentgoal.usecase.RegisterTreatmentGoalUseCase;
import com.backintro.application.treatmentgoal.usecase.UpdateTreatmentGoalUseCase;
import com.backintro.domain.treatmentgoal.port.repository.TreatmentGoalRepository;
import com.backintro.domain.treatmentgoalstatus.port.repository.TreatmentGoalStatusRepository;
import com.backintro.domain.treatmentplan.port.repository.TreatmentPlanRepository;

@Configuration
public class TreatmentGoalBeanConfiguration {

    @Bean
    RegisterTreatmentGoalUseCase registerTreatmentGoalUseCase(
            TreatmentGoalRepository treatmentGoalRepository,
            TreatmentPlanRepository treatmentPlanRepository,
            TreatmentGoalStatusRepository statusRepository
    ) {
        return new RegisterTreatmentGoalUseCase(
                treatmentGoalRepository,
                treatmentPlanRepository,
                statusRepository
        );
    }

    @Bean
    GetTreatmentGoalByIdUseCase getTreatmentGoalByIdUseCase(
            TreatmentGoalRepository repository
    ) {
        return new GetTreatmentGoalByIdUseCase(repository);
    }

    @Bean
    ListTreatmentGoalUseCase listTreatmentGoalUseCase(
            TreatmentGoalRepository repository
    ) {
        return new ListTreatmentGoalUseCase(repository);
    }

    @Bean
    UpdateTreatmentGoalUseCase updateTreatmentGoalUseCase(
            TreatmentGoalRepository treatmentGoalRepository,
            TreatmentGoalStatusRepository statusRepository
    ) {
        return new UpdateTreatmentGoalUseCase(
                treatmentGoalRepository,
                statusRepository
        );
    }

    @Bean
    DeleteTreatmentGoalUseCase deleteTreatmentGoalUseCase(
            TreatmentGoalRepository repository
    ) {
        return new DeleteTreatmentGoalUseCase(repository);
    }
}
