package com.backintro.application.medicationroute.usecase;

import com.backintro.application.medicationroute.command.RegisterMedicationRouteCommand;
import com.backintro.application.medicationroute.dto.MedicationRouteResponse;
import com.backintro.domain.medicationroute.model.aggregate.MedicationRoute;
import com.backintro.domain.medicationroute.port.repository.MedicationRouteRepository;

public class RegisterMedicationRouteUseCase {

    private final MedicationRouteRepository medicationRouteRepository;

    public RegisterMedicationRouteUseCase(
            MedicationRouteRepository medicationRouteRepository
    ) {
        this.medicationRouteRepository = medicationRouteRepository;
    }

    public MedicationRouteResponse execute(
            RegisterMedicationRouteCommand command
    ) {
        MedicationRoute medicationRoute = MedicationRoute.register(
                command.name(),
                command.code()
        );

        MedicationRoute saved =
                medicationRouteRepository.save(medicationRoute);

        return new MedicationRouteResponse(
                saved.id().value(),
                saved.name(),
                saved.code()
        );
    }
}
