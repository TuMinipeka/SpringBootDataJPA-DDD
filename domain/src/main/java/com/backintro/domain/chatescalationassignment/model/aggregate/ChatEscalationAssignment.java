package com.backintro.domain.chatescalationassignment.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationassignment.event.ChatEscalationAssignmentRegisteredEvent;
import com.backintro.domain.chatescalationassignment.event.ChatEscalationAssignmentUpdatedEvent;
import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public class ChatEscalationAssignment extends AggregateRoot {

    private final ChatEscalationAssignmentId id;
    private final ChatEscalationId escalationId;
    private ProfessionalId professionalId;

    private ChatEscalationAssignment(
            ChatEscalationAssignmentId id,
            ChatEscalationId escalationId,
            ProfessionalId professionalId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.escalationId = Objects.requireNonNull(
                escalationId,
                "escalationId must not be null"
        );
        this.professionalId = Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
    }

    public static ChatEscalationAssignment register(
            ChatEscalationId escalationId,
            ProfessionalId professionalId
    ) {
        ChatEscalationAssignmentId id =
                ChatEscalationAssignmentId.generate();
        ChatEscalationAssignment assignment =
                new ChatEscalationAssignment(
                        id,
                        escalationId,
                        professionalId
                );

        assignment.recordEvent(
                new ChatEscalationAssignmentRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return assignment;
    }

    public static ChatEscalationAssignment restore(
            ChatEscalationAssignmentId id,
            ChatEscalationId escalationId,
            ProfessionalId professionalId
    ) {
        return new ChatEscalationAssignment(
                id,
                escalationId,
                professionalId
        );
    }

    public void update(ProfessionalId professionalId) {
        this.professionalId = Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );

        recordEvent(
                new ChatEscalationAssignmentUpdatedEvent(
                        this.id,
                        this.professionalId,
                        LocalDateTime.now()
                )
        );
    }

    public ChatEscalationAssignmentId id() {
        return id;
    }

    public ChatEscalationId escalationId() {
        return escalationId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }
}
