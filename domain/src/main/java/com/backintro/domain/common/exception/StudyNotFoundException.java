package com.backintro.domain.common.exception;

import com.backintro.domain.study.model.valueobject.StudyId;

public class StudyNotFoundException extends RuntimeException {

    public StudyNotFoundException(StudyId id) {
        super("Study not found with id: " + id.value());
    }
}
