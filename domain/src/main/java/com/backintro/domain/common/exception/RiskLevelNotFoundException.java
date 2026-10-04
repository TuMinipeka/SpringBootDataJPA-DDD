package com.backintro.domain.common.exception;

import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public class RiskLevelNotFoundException extends RuntimeException {

    public RiskLevelNotFoundException(RiskLevelId id) {
        super("Risk level not found with id: " + id.value());
    }
}
