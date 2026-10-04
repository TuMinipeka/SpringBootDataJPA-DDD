package com.backintro.domain.common.exception;

import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

public class StateRegionNotFoundException extends RuntimeException {

    public StateRegionNotFoundException(StateRegionId id) {
        super("State region not found with id: " + id.value());
    }
}
