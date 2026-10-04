package com.backintro.domain.chatparticipant.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.common.event.DomainEvent;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;

public record ChatParticipantUpdatedEvent(
        ChatParticipantId id,
        SenderTypeId participantTypeId,
        PatientId patientId,
        ProfessionalId professionalId,
        LocalDateTime occurredOn
) implements DomainEvent {

    public ChatParticipantUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(
                participantTypeId,
                "participantTypeId must not be null"
        );
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
