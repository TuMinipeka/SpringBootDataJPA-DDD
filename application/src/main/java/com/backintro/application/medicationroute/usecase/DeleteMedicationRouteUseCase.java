package com.backintro.application.medicationroute.usecase;

import java.time.LocalDateTime;

import com.backintro.application.medicationroute.exception.MedicationRouteNotFoundApplicationException;
import com.backintro.domain.medicationroute.event.MedicationRouteDeletedEvent;
import com.backintro.domain.medicationroute.model.valueobject.MedicationRouteId;
import com.backintro.domain.medicationroute.port.repository.MedicationRouteRepository;

public class DeleteMedicationRouteUseCase {

    private final MedicationRouteRepository medicationRouteRepository;

    public DeleteMedicationRouteUseCase(
            MedicationRouteRepository medicationRouteRepository
    ) {
        this.medicationRouteRepository = medicationRouteRepository;
    }

    public MedicationRouteDeletedEvent execute(MedicationRouteId id) {
        var medicationRoute = medicationRouteRepository.findById(id)
                .orElseThrow(() ->
                        new MedicationRouteNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        medicationRouteRepository.delete(medicationRoute);

        return new MedicationRouteDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
