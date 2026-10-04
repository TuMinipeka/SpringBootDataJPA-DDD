package com.backintro.application.gender.dto;

import java.util.UUID;

public record GenderResponse(
        UUID id,
        String description
) {
}
