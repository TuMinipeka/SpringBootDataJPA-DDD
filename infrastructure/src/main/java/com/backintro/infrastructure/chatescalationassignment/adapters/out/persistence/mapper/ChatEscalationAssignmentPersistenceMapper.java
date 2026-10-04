package com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;

@Component
public class ChatEscalationAssignmentPersistenceMapper {

    public ChatEscalationAssignment toDomain(
            ChatEscalationAssignmentJpaEntity entity
    ) {
        return ChatEscalationAssignment.restore(
                new ChatEscalationAssignmentId(entity.getId()),
                new ChatEscalationId(entity.getEscalationId()),
                new ProfessionalId(entity.getProfessionalId())
        );
    }

    public ChatEscalationAssignmentJpaEntity toNewEntity(
            ChatEscalationAssignment assignment
    ) {
        return new ChatEscalationAssignmentJpaEntity(
                assignment.id().value(),
                assignment.escalationId().value(),
                assignment.professionalId().value()
        );
    }

    public void synchronize(
            ChatEscalationAssignment assignment,
            ChatEscalationAssignmentJpaEntity entity
    ) {
        entity.synchronize(assignment.professionalId().value());
    }
}
