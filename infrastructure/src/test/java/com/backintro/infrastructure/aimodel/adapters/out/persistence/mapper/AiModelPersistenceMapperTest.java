package com.backintro.infrastructure.aimodel.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;

class AiModelPersistenceMapperTest {

    private final AiModelPersistenceMapper mapper =
            new AiModelPersistenceMapper();

    @Test
    void mapsAiModelInBothDirectionsWithoutCreatingDomainEvents() {
        AiModel original = AiModel.register(
                AiProviderModelId.generate(),
                "GPT-4.1",
                "gpt-4.1",
                new BigDecimal("0.00000200"),
                new BigDecimal("0.00000800"),
                32768,
                1048576
        );

        AiModel restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.providerModelId())
                .isEqualTo(original.providerModelId());
        assertThat(restored.nameModel()).isEqualTo("GPT-4.1");
        assertThat(restored.modelKey()).isEqualTo("gpt-4.1");
        assertThat(restored.inputTokenPrice())
                .isEqualByComparingTo("0.00000200");
        assertThat(restored.outputTokenPrice())
                .isEqualByComparingTo("0.00000800");
        assertThat(restored.maxTokens()).isEqualTo(32768);
        assertThat(restored.contextWindow()).isEqualTo(1048576);
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
