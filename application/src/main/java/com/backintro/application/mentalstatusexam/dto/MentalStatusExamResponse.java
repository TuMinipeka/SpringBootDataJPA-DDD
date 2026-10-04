package com.backintro.application.mentalstatusexam.dto;

import java.util.UUID;

public record MentalStatusExamResponse(
        UUID id,
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
}
