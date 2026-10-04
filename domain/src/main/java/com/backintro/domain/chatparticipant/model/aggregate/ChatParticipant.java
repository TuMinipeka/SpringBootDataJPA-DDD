package com.backintro.domain.chatparticipant.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatconversation.model.valueobject.ChatConversationId;
import com.backintro.domain.chatparticipant.event.ChatParticipantRegisteredEvent;
import com.backintro.domain.chatparticipant.event.ChatParticipantUpdatedEvent;
import com.backintro.domain.chatparticipant.model.valueobject.ChatParticipantId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;

public class ChatParticipant extends AggregateRoot {

    private final ChatParticipantId id;
    private final ChatConversationId conversationId;
    private SenderTypeId participantTypeId;
    private PatientId patientId;
    private ProfessionalId professionalId;

    private ChatParticipant(
            ChatParticipantId id,
            ChatConversationId conversationId,
            SenderTypeId participantTypeId,
            PatientId patientId,
            ProfessionalId professionalId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.conversationId = Objects.requireNonNull(
                conversationId,
                "conversationId must not be null"
        );
        this.participantTypeId = Objects.requireNonNull(
                participantTypeId,
                "participantTypeId must not be null"
        );
        validateActor(patientId, professionalId);
        this.patientId = patientId;
        this.professionalId = professionalId;
    }

    public static ChatParticipant register(
            ChatConversationId conversationId,
            SenderTypeId participantTypeId,
            PatientId patientId,
            ProfessionalId professionalId
    ) {
        ChatParticipantId id = ChatParticipantId.generate();
        ChatParticipant chatParticipant = new ChatParticipant(
                id,
                conversationId,
                participantTypeId,
                patientId,
                professionalId
        );

        chatParticipant.recordEvent(
                new ChatParticipantRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return chatParticipant;
    }

    public static ChatParticipant restore(
            ChatParticipantId id,
            ChatConversationId conversationId,
            SenderTypeId participantTypeId,
            PatientId patientId,
            ProfessionalId professionalId
    ) {
        return new ChatParticipant(
                id,
                conversationId,
                participantTypeId,
                patientId,
                professionalId
        );
    }

    public void update(
            SenderTypeId participantTypeId,
            PatientId patientId,
            ProfessionalId professionalId
    ) {
        this.participantTypeId = Objects.requireNonNull(
                participantTypeId,
                "participantTypeId must not be null"
        );
        validateActor(patientId, professionalId);
        this.patientId = patientId;
        this.professionalId = professionalId;

        recordEvent(
                new ChatParticipantUpdatedEvent(
                        this.id,
                        this.participantTypeId,
                        this.patientId,
                        this.professionalId,
                        LocalDateTime.now()
                )
        );
    }

    private static void validateActor(
            PatientId patientId,
            ProfessionalId professionalId
    ) {
        if (patientId != null && professionalId != null) {
            throw new IllegalArgumentException(
                    "patientId and professionalId cannot both be present"
            );
        }
    }

    public ChatParticipantId id() {
        return id;
    }

    public ChatConversationId conversationId() {
        return conversationId;
    }

    public SenderTypeId participantTypeId() {
        return participantTypeId;
    }

    public PatientId patientId() {
        return patientId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }
}
