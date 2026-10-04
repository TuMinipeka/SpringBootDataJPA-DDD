package com.backintro.domain.common.exception;

import com.backintro.domain.priority.model.valueobject.PriorityId;

public class PriorityNotFoundException extends RuntimeException {

    public PriorityNotFoundException(PriorityId id) {
        super("Priority not found with id: " + id.value());
    }
}
