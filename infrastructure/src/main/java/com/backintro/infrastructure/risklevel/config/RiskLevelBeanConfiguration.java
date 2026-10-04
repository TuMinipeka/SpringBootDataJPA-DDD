package com.backintro.infrastructure.risklevel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.risklevel.usecase.DeleteRiskLevelUseCase;
import com.backintro.application.risklevel.usecase.GetRiskLevelByIdUseCase;
import com.backintro.application.risklevel.usecase.ListRiskLevelUseCase;
import com.backintro.application.risklevel.usecase.RegisterRiskLevelUseCase;
import com.backintro.application.risklevel.usecase.UpdateRiskLevelUseCase;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;

@Configuration
public class RiskLevelBeanConfiguration {

    @Bean
    RegisterRiskLevelUseCase registerRiskLevelUseCase(
            RiskLevelRepository repository
    ) {
        return new RegisterRiskLevelUseCase(repository);
    }

    @Bean
    GetRiskLevelByIdUseCase getRiskLevelByIdUseCase(
            RiskLevelRepository repository
    ) {
        return new GetRiskLevelByIdUseCase(repository);
    }

    @Bean
    ListRiskLevelUseCase listRiskLevelUseCase(
            RiskLevelRepository repository
    ) {
        return new ListRiskLevelUseCase(repository);
    }

    @Bean
    UpdateRiskLevelUseCase updateRiskLevelUseCase(
            RiskLevelRepository repository
    ) {
        return new UpdateRiskLevelUseCase(repository);
    }

    @Bean
    DeleteRiskLevelUseCase deleteRiskLevelUseCase(
            RiskLevelRepository repository
    ) {
        return new DeleteRiskLevelUseCase(repository);
    }
}
