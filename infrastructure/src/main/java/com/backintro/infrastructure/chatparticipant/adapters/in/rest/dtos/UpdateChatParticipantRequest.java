package com.backintro.infrastructure.chatparticipant.adapters.in.rest.dtos;

import java.util.UUID;

import jakarta.validation.constraints.NotNull;

public record UpdateChatParticipantRequest(

        @NotNull(message = "participantTypeId is required")
        UUID participantTypeId,

        UUID patientId,

        UUID professionalId

) {
}
