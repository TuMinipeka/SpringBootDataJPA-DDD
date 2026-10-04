package com.backintro.domain.common.exception;

import com.backintro.domain.medicationroute.model.valueobject.MedicationRouteId;

public class MedicationRouteNotFoundException extends RuntimeException {

    public MedicationRouteNotFoundException(MedicationRouteId id) {
        super("Medication route not found with id: " + id.value());
    }
}
