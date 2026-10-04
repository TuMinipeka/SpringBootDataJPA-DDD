package com.backintro.application.chatparticipant.dto;

import java.util.UUID;

public record ChatParticipantResponse(
        UUID id,
        UUID conversationId,
        UUID participantTypeId,
        UUID patientId,
        UUID professionalId
) {
}
