package com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;

class ChatAiRunMetricPersistenceMapperTest {

    private final ChatAiRunMetricPersistenceMapper mapper =
            new ChatAiRunMetricPersistenceMapper();

    @Test
    void mapsMetricInBothDirectionsWithoutCreatingDomainEvents() {
        ChatAiRunMetric original = ChatAiRunMetric.register(
                ChatAiRunId.generate(),
                120,
                80,
                200,
                new BigDecimal("0.012345")
        );

        ChatAiRunMetric restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.aiRunId()).isEqualTo(original.aiRunId());
        assertThat(restored.promptTokens()).isEqualTo(120);
        assertThat(restored.completionTokens()).isEqualTo(80);
        assertThat(restored.totalTokens()).isEqualTo(200);
        assertThat(restored.cost()).isEqualByComparingTo("0.012345");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
