package com.backintro.application.documenttype.dto;

import java.util.UUID;

public record DocumentTypeResponse(
        UUID id,
        String name,
        String code
) {
}
