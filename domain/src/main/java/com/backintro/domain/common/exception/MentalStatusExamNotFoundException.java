package com.backintro.domain.common.exception;

import com.backintro.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public class MentalStatusExamNotFoundException extends RuntimeException {

    public MentalStatusExamNotFoundException(MentalStatusExamId id) {
        super("Mental status exam not found with id: " + id.value());
    }
}
