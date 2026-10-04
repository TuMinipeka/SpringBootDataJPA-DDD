package com.backintro.application.medicationroute.usecase;

import com.backintro.application.medicationroute.dto.MedicationRouteResponse;
import com.backintro.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import com.backintro.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.backintro.domain.medicationroute.port.repository.MedicationRouteRepository;

public class GetMedicationRouteByIdUseCase {

    private final MedicationRouteRepository medicationRouteRepository;

    public GetMedicationRouteByIdUseCase(
            MedicationRouteRepository medicationRouteRepository
    ) {
        this.medicationRouteRepository = medicationRouteRepository;
    }

    public MedicationRouteResponse execute(MedicationRouteId id) {
        var medicationRoute = medicationRouteRepository.findById(id)
                .orElseThrow(() ->
                        new MedicationRouteNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new MedicationRouteResponse(
                medicationRoute.id().value(),
                medicationRoute.name(),
                medicationRoute.code()
        );
    }
}
