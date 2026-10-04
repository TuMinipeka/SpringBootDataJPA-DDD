package com.backintro.application.study.dto;

import java.util.UUID;

public record StudyResponse(
        UUID id,
        String name
) {
}
