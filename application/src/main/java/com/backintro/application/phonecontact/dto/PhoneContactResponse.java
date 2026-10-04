package com.backintro.application.phonecontact.dto;

import java.util.UUID;

public record PhoneContactResponse(
        UUID id,
        String phone,
        String notes
) {
}
