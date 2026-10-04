package com.backintro.application.priority.dto;

import java.util.UUID;

public record PriorityResponse(
        UUID id,
        String namePriority
) {
}
