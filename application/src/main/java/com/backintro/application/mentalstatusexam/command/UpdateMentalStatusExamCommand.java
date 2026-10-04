package com.backintro.application.mentalstatusexam.command;

import java.util.Objects;

import com.backintro.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public record UpdateMentalStatusExamCommand(
        MentalStatusExamId id,
        String appearance,
        String behavior,
        String attitude,
        String consciousness,
        String orientation,
        String attention,
        String memory,
        String speech,
        String mood,
        String affect,
        String thoughtProcess,
        String thoughtContent,
        String perception,
        String judgment,
        String insight,
        String psychomotorActivity,
        String observations
) {

    public UpdateMentalStatusExamCommand {
        Objects.requireNonNull(id, "id must not be null");
    }
}
