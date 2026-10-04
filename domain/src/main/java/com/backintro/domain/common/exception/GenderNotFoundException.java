package com.backintro.domain.common.exception;

import com.backintro.domain.gender.model.valueobject.GenderId;

public class GenderNotFoundException extends RuntimeException {

    public GenderNotFoundException(GenderId id) {
        super("Gender not found with id: " + id.value());
    }
}
