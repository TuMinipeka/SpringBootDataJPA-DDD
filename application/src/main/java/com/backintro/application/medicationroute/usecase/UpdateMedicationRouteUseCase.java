package com.backintro.application.medicationroute.usecase;

import com.backintro.application.medicationroute.command.UpdateMedicationRouteCommand;
import com.backintro.application.medicationroute.dto.MedicationRouteResponse;
import com.backintro.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import com.backintro.domain.medicationroute.port.repository.MedicationRouteRepository;

public class UpdateMedicationRouteUseCase {

    private final MedicationRouteRepository medicationRouteRepository;

    public UpdateMedicationRouteUseCase(
            MedicationRouteRepository medicationRouteRepository
    ) {
        this.medicationRouteRepository = medicationRouteRepository;
    }

    public MedicationRouteResponse execute(
            UpdateMedicationRouteCommand command
    ) {
        var medicationRoute = medicationRouteRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new MedicationRouteNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        medicationRoute.update(command.name(), command.code());

        var updated = medicationRouteRepository.save(medicationRoute);

        return new MedicationRouteResponse(
                updated.id().value(),
                updated.name(),
                updated.code()
        );
    }
}
