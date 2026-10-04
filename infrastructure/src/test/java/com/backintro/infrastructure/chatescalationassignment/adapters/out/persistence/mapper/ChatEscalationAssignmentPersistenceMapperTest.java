package com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

class ChatEscalationAssignmentPersistenceMapperTest {

    private final ChatEscalationAssignmentPersistenceMapper mapper =
            new ChatEscalationAssignmentPersistenceMapper();

    @Test
    void mapsAssignmentInBothDirectionsWithoutCreatingDomainEvents() {
        ChatEscalationAssignment original =
                ChatEscalationAssignment.register(
                        ChatEscalationId.generate(),
                        ProfessionalId.generate()
                );

        ChatEscalationAssignment restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.escalationId())
                .isEqualTo(original.escalationId());
        assertThat(restored.professionalId())
                .isEqualTo(original.professionalId());
        assertThat(restored.domainEvents()).isEmpty();
    }
}
