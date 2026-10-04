package com.backintro.infrastructure.mentalstatusexam.adapters.in.rest.dtos;

public record UpdateMentalStatusExamRequest(

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
