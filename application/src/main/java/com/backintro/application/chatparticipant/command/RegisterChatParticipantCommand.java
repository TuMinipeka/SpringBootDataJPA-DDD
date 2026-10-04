package com.backintro.application.chatparticipant.command;

import java.util.Objects;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;

public record RegisterChatParticipantCommand(
        ChatConversationId conversationId,
        SenderTypeId participantTypeId,
        PatientId patientId,
        ProfessionalId professionalId
) {

    public RegisterChatParticipantCommand {
        Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
        Objects.requireNonNull(
                participantTypeId,
                "participantTypeId must not be null"
        );
    }
}
