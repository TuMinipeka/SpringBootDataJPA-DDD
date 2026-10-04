package com.backintro.infrastructure.risklevel.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.risklevel.model.aggregate.RiskLevel;

class RiskLevelPersistenceMapperTest {

    private final RiskLevelPersistenceMapper mapper =
            new RiskLevelPersistenceMapper();

    @Test
    void mapsRiskLevelInBothDirectionsWithoutCreatingDomainEvents() {
        RiskLevel original = RiskLevel.register(
                "High",
                "HIGH",
                3
        );

        RiskLevel restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("High");
        assertThat(restored.code()).isEqualTo("HIGH");
        assertThat(restored.active()).isTrue();
        assertThat(restored.severity()).isEqualTo(3);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
