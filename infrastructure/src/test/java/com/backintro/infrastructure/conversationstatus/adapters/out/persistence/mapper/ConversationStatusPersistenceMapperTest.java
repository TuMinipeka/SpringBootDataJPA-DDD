package com.backintro.infrastructure.conversationstatus.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.conversationstatus.model.aggregate.ConversationStatus;

class ConversationStatusPersistenceMapperTest {

    private final ConversationStatusPersistenceMapper mapper =
            new ConversationStatusPersistenceMapper();

    @Test
    void mapsConversationStatusInBothDirectionsWithoutCreatingDomainEvents() {
        ConversationStatus original = ConversationStatus.register("Open");

        ConversationStatus restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.nameStatus()).isEqualTo("Open");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
