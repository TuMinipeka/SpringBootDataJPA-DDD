package com.backintro.infrastructure.stateregion.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.stateregion.usecase.DeleteStateRegionUseCase;
import com.backintro.application.stateregion.usecase.GetStateRegionByIdUseCase;
import com.backintro.application.stateregion.usecase.ListStateRegionUseCase;
import com.backintro.application.stateregion.usecase.RegisterStateRegionUseCase;
import com.backintro.application.stateregion.usecase.UpdateStateRegionUseCase;
import com.backintro.domain.country.port.repository.CountryRepository;
import com.backintro.domain.stateregion.port.repository.StateRegionRepository;

@Configuration
public class StateRegionBeanConfiguration {

    @Bean
    RegisterStateRegionUseCase registerStateRegionUseCase(
            StateRegionRepository stateRegionRepository,
            CountryRepository countryRepository
    ) {
        return new RegisterStateRegionUseCase(
                stateRegionRepository,
                countryRepository
        );
    }

    @Bean
    GetStateRegionByIdUseCase getStateRegionByIdUseCase(
            StateRegionRepository repository
    ) {
        return new GetStateRegionByIdUseCase(repository);
    }

    @Bean
    ListStateRegionUseCase listStateRegionUseCase(
            StateRegionRepository repository
    ) {
        return new ListStateRegionUseCase(repository);
    }

    @Bean
    UpdateStateRegionUseCase updateStateRegionUseCase(
            StateRegionRepository repository
    ) {
        return new UpdateStateRegionUseCase(repository);
    }

    @Bean
    DeleteStateRegionUseCase deleteStateRegionUseCase(
            StateRegionRepository repository
    ) {
        return new DeleteStateRegionUseCase(repository);
    }
}
