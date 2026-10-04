package com.backintro.infrastructure.airunstatus.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.airunstatus.model.aggregate.AiRunStatus;

class AiRunStatusPersistenceMapperTest {

    private final AiRunStatusPersistenceMapper mapper =
            new AiRunStatusPersistenceMapper();

    @Test
    void mapsAiRunStatusInBothDirectionsWithoutCreatingDomainEvents() {
        AiRunStatus original = AiRunStatus.register("Pending");

        AiRunStatus restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.nameStatus()).isEqualTo("Pending");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
