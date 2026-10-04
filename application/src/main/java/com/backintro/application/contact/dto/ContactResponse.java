package com.backintro.application.contact.dto;

import java.util.UUID;

public record ContactResponse(
        UUID id,
        String fullName,
        String email,
        String notes,
        UUID cityId
) {
}
