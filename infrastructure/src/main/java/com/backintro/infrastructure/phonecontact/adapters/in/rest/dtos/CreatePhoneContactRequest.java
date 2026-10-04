package com.backintro.infrastructure.phonecontact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CreatePhoneContactRequest(

        @NotNull(message = "contactId is required")
        UUID contactId,

        @NotBlank(message = "phone is required")
        @Size(max = 30, message = "phone must have at most 30 characters")
        String phone,

        String notes

) {
}
