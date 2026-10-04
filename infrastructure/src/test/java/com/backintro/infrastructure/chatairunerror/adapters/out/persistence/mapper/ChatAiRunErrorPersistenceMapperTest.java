package com.backintro.infrastructure.chatairunerror.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunerror.model.aggregate.ChatAiRunError;

class ChatAiRunErrorPersistenceMapperTest {

    private final ChatAiRunErrorPersistenceMapper mapper =
            new ChatAiRunErrorPersistenceMapper();

    @Test
    void mapsErrorInBothDirectionsWithoutCreatingDomainEvents() {
        ChatAiRunError original = ChatAiRunError.register(
                ChatAiRunId.generate(),
                "Provider request failed",
                "TIMEOUT",
                "provider-error-123"
        );

        ChatAiRunError restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.aiRunId()).isEqualTo(original.aiRunId());
        assertThat(restored.errorMessage())
                .isEqualTo("Provider request failed");
        assertThat(restored.errorCode()).isEqualTo("TIMEOUT");
        assertThat(restored.providerErrorId())
                .isEqualTo("provider-error-123");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
