package com.backintro.infrastructure.riskassessment.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.riskassessment.usecase.DeleteRiskAssessmentUseCase;
import com.backintro.application.riskassessment.usecase.GetRiskAssessmentByIdUseCase;
import com.backintro.application.riskassessment.usecase.ListRiskAssessmentUseCase;
import com.backintro.application.riskassessment.usecase.RegisterRiskAssessmentUseCase;
import com.backintro.application.riskassessment.usecase.UpdateRiskAssessmentUseCase;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.riskassessment.port.repository.RiskAssessmentRepository;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;

@Configuration
public class RiskAssessmentBeanConfiguration {

    @Bean
    RegisterRiskAssessmentUseCase registerRiskAssessmentUseCase(
            RiskAssessmentRepository riskAssessmentRepository,
            EncounterRepository encounterRepository,
            RiskLevelRepository riskLevelRepository,
            ProfessionalRepository professionalRepository
    ) {
        return new RegisterRiskAssessmentUseCase(
                riskAssessmentRepository,
                encounterRepository,
                riskLevelRepository,
                professionalRepository
        );
    }

    @Bean
    GetRiskAssessmentByIdUseCase getRiskAssessmentByIdUseCase(
            RiskAssessmentRepository repository
    ) {
        return new GetRiskAssessmentByIdUseCase(repository);
    }

    @Bean
    ListRiskAssessmentUseCase listRiskAssessmentUseCase(
            RiskAssessmentRepository repository
    ) {
        return new ListRiskAssessmentUseCase(repository);
    }

    @Bean
    UpdateRiskAssessmentUseCase updateRiskAssessmentUseCase(
            RiskAssessmentRepository riskAssessmentRepository,
            RiskLevelRepository riskLevelRepository
    ) {
        return new UpdateRiskAssessmentUseCase(
                riskAssessmentRepository,
                riskLevelRepository
        );
    }

    @Bean
    DeleteRiskAssessmentUseCase deleteRiskAssessmentUseCase(
            RiskAssessmentRepository repository
    ) {
        return new DeleteRiskAssessmentUseCase(repository);
    }
}
