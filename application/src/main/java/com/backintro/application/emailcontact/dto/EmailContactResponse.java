package com.backintro.application.emailcontact.dto;

import java.util.UUID;

public record EmailContactResponse(
        UUID id,
        String email,
        String notes
) {
}
