package com.backintro.infrastructure.medicationroute.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.medicationroute.usecase.DeleteMedicationRouteUseCase;
import com.backintro.application.medicationroute.usecase.GetMedicationRouteByIdUseCase;
import com.backintro.application.medicationroute.usecase.ListMedicationRouteUseCase;
import com.backintro.application.medicationroute.usecase.RegisterMedicationRouteUseCase;
import com.backintro.application.medicationroute.usecase.UpdateMedicationRouteUseCase;
import com.backintro.domain.medicationroute.port.repository.MedicationRouteRepository;

@Configuration
public class MedicationRouteBeanConfiguration {

    @Bean
    RegisterMedicationRouteUseCase registerMedicationRouteUseCase(
            MedicationRouteRepository repository
    ) {
        return new RegisterMedicationRouteUseCase(repository);
    }

    @Bean
    GetMedicationRouteByIdUseCase getMedicationRouteByIdUseCase(
            MedicationRouteRepository repository
    ) {
        return new GetMedicationRouteByIdUseCase(repository);
    }

    @Bean
    ListMedicationRouteUseCase listMedicationRouteUseCase(
            MedicationRouteRepository repository
    ) {
        return new ListMedicationRouteUseCase(repository);
    }

    @Bean
    UpdateMedicationRouteUseCase updateMedicationRouteUseCase(
            MedicationRouteRepository repository
    ) {
        return new UpdateMedicationRouteUseCase(repository);
    }

    @Bean
    DeleteMedicationRouteUseCase deleteMedicationRouteUseCase(
            MedicationRouteRepository repository
    ) {
        return new DeleteMedicationRouteUseCase(repository);
    }
}
