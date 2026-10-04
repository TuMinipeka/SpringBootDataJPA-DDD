package com.backintro.infrastructure.citymunicipality.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.citymunicipality.usecase.DeleteCityMunicipalityUseCase;
import com.backintro.application.citymunicipality.usecase.GetCityMunicipalityByIdUseCase;
import com.backintro.application.citymunicipality.usecase.ListCityMunicipalityUseCase;
import com.backintro.application.citymunicipality.usecase.RegisterCityMunicipalityUseCase;
import com.backintro.application.citymunicipality.usecase.UpdateCityMunicipalityUseCase;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.backintro.domain.stateregion.port.repository.StateRegionRepository;

@Configuration
public class CityMunicipalityBeanConfiguration {

    @Bean
    RegisterCityMunicipalityUseCase registerCityMunicipalityUseCase(
            CityMunicipalityRepository cityMunicipalityRepository,
            StateRegionRepository stateRegionRepository
    ) {
        return new RegisterCityMunicipalityUseCase(
                cityMunicipalityRepository,
                stateRegionRepository
        );
    }

    @Bean
    GetCityMunicipalityByIdUseCase getCityMunicipalityByIdUseCase(
            CityMunicipalityRepository repository
    ) {
        return new GetCityMunicipalityByIdUseCase(repository);
    }

    @Bean
    ListCityMunicipalityUseCase listCityMunicipalityUseCase(
            CityMunicipalityRepository repository
    ) {
        return new ListCityMunicipalityUseCase(repository);
    }

    @Bean
    UpdateCityMunicipalityUseCase updateCityMunicipalityUseCase(
            CityMunicipalityRepository repository
    ) {
        return new UpdateCityMunicipalityUseCase(repository);
    }

    @Bean
    DeleteCityMunicipalityUseCase deleteCityMunicipalityUseCase(
            CityMunicipalityRepository repository
    ) {
        return new DeleteCityMunicipalityUseCase(repository);
    }
}
