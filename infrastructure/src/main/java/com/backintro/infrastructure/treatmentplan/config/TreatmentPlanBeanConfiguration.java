package com.backintro.infrastructure.treatmentplan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.treatmentplan.usecase.DeleteTreatmentPlanUseCase;
import com.backintro.application.treatmentplan.usecase.GetTreatmentPlanByIdUseCase;
import com.backintro.application.treatmentplan.usecase.ListTreatmentPlanUseCase;
import com.backintro.application.treatmentplan.usecase.RegisterTreatmentPlanUseCase;
import com.backintro.application.treatmentplan.usecase.UpdateTreatmentPlanUseCase;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.treatmentplan.port.repository.TreatmentPlanRepository;
import com.backintro.domain.treatmentstatus.port.repository.TreatmentStatusRepository;

@Configuration
public class TreatmentPlanBeanConfiguration {

    @Bean
    RegisterTreatmentPlanUseCase registerTreatmentPlanUseCase(
            TreatmentPlanRepository treatmentPlanRepository,
            EncounterRepository encounterRepository,
            ProfessionalRepository professionalRepository,
            TreatmentStatusRepository statusRepository
    ) {
        return new RegisterTreatmentPlanUseCase(
                treatmentPlanRepository,
                encounterRepository,
                professionalRepository,
                statusRepository
        );
    }

    @Bean
    GetTreatmentPlanByIdUseCase getTreatmentPlanByIdUseCase(
            TreatmentPlanRepository repository
    ) {
        return new GetTreatmentPlanByIdUseCase(repository);
    }

    @Bean
    ListTreatmentPlanUseCase listTreatmentPlanUseCase(
            TreatmentPlanRepository repository
    ) {
        return new ListTreatmentPlanUseCase(repository);
    }

    @Bean
    UpdateTreatmentPlanUseCase updateTreatmentPlanUseCase(
            TreatmentPlanRepository treatmentPlanRepository,
            TreatmentStatusRepository statusRepository
    ) {
        return new UpdateTreatmentPlanUseCase(
                treatmentPlanRepository,
                statusRepository
        );
    }

    @Bean
    DeleteTreatmentPlanUseCase deleteTreatmentPlanUseCase(
            TreatmentPlanRepository repository
    ) {
        return new DeleteTreatmentPlanUseCase(repository);
    }
}
