package com.backintro.infrastructure.chatescalationstatushistory.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationstatushistory.model.aggregate.ChatEscalationStatusHistory;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

class ChatEscalationStatusHistoryPersistenceMapperTest {

    private final ChatEscalationStatusHistoryPersistenceMapper mapper =
            new ChatEscalationStatusHistoryPersistenceMapper();

    @Test
    void mapsHistoryInBothDirectionsWithoutCreatingDomainEvents() {
        ChatEscalationStatusHistory original =
                ChatEscalationStatusHistory.register(
                        ChatEscalationId.generate(),
                        EscalationStatusId.generate()
                );

        ChatEscalationStatusHistory restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.escalationId())
                .isEqualTo(original.escalationId());
        assertThat(restored.escalationStatusId())
                .isEqualTo(original.escalationStatusId());
        assertThat(restored.domainEvents()).isEmpty();
    }
}
