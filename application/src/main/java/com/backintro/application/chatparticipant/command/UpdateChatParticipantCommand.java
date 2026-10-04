package com.backintro.application.chatparticipant.command;

import java.util.Objects;

import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;

public record UpdateChatParticipantCommand(
        ChatParticipantId id,
        SenderTypeId participantTypeId,
        PatientId patientId,
        ProfessionalId professionalId
) {

    public UpdateChatParticipantCommand {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                participantTypeId,
                "participantTypeId must not be null"
        );
    }
}
