package com.backintro.infrastructure.patientcontact.adapters.in.rest.dtos;

public record UpdatePatientContactRequest(

        boolean primaryContact,

        boolean emergencyContact

) {
}
