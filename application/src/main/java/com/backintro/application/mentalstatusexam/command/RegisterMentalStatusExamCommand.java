package com.backintro.application.mentalstatusexam.command;

import java.util.Objects;

import com.backintro.domain.encounter.model.valueobject.EncounterId;

public record RegisterMentalStatusExamCommand(
        EncounterId encounterId,
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

    public RegisterMentalStatusExamCommand {
        Objects.requireNonNull(encounterId, "encounterId must not be null");
    }
}
