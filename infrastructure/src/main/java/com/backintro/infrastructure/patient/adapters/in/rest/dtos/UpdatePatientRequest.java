package com.backintro.infrastructure.patient.adapters.in.rest.dtos;

import java.time.LocalDate;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record UpdatePatientRequest(

        @NotBlank(message = "documentNumber is required")
        @Size(max = 30, message = "documentNumber must have at most 30 characters")
        String documentNumber,

        @NotBlank(message = "firstName is required")
        @Size(max = 50, message = "firstName must have at most 50 characters")
        String firstName,

        @Size(max = 50, message = "middleName must have at most 50 characters")
        String middleName,

        @NotBlank(message = "lastName is required")
        @Size(max = 50, message = "lastName must have at most 50 characters")
        String lastName,

        @Size(max = 50, message = "secondLastName must have at most 50 characters")
        String secondLastName,

        @NotNull(message = "birthDate is required")
        @PastOrPresent(message = "birthDate must not be in the future")
        LocalDate birthDate,

        @Email(message = "email must be valid")
        @Size(max = 150, message = "email must have at most 150 characters")
        String email,

        @Size(max = 30, message = "phone must have at most 30 characters")
        String phone,

        @Size(max = 250, message = "address must have at most 250 characters")
        String address

) {
}
