package com.backintro.application.patientcontact.dto;

import java.util.UUID;

public record PatientContactResponse(
        UUID id,
        boolean primaryContact,
        boolean emergencyContact
) {
}
