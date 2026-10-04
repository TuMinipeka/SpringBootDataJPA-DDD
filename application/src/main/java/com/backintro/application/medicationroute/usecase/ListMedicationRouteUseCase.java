package com.backintro.application.medicationroute.usecase;

import java.util.List;

import com.backintro.application.medicationroute.dto.MedicationRouteResponse;
import com.backintro.domain.medicationroute.port.repository.MedicationRouteRepository;

public class ListMedicationRouteUseCase {

    private final MedicationRouteRepository medicationRouteRepository;

    public ListMedicationRouteUseCase(
            MedicationRouteRepository medicationRouteRepository
    ) {
        this.medicationRouteRepository = medicationRouteRepository;
    }

    public List<MedicationRouteResponse> execute() {
        return medicationRouteRepository.findAll()
                .stream()
                .map(medicationRoute ->
                        new MedicationRouteResponse(
                                medicationRoute.id().value(),
                                medicationRoute.name(),
                                medicationRoute.code()
                        )
                )
                .toList();
    }
}
