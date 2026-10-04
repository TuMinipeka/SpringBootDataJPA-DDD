package com.backintro.infrastructure.contact.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UpdateContactRequest(

        @NotBlank(message = "fullName is required")
        @Size(max = 200, message = "fullName must have at most 200 characters")
        String fullName,

        @Email(message = "email must be valid")
        @Size(max = 150, message = "email must have at most 150 characters")
        String email,

        String notes,

        UUID cityId

) {
}
