package com.backintro.application.study.command;

import java.util.Objects;

public record RegisterStudyCommand(
        String name
) {

    public RegisterStudyCommand {
        Objects.requireNonNull(name, "name must not be null");
    }
}
