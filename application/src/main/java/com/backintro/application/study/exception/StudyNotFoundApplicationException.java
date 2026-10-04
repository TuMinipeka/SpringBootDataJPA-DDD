package com.backintro.application.study.exception;

import com.backintro.application.common.exception.ApplicationException;

public class StudyNotFoundApplicationException extends ApplicationException {

    public StudyNotFoundApplicationException(String message) {
        super(message);
    }
}
