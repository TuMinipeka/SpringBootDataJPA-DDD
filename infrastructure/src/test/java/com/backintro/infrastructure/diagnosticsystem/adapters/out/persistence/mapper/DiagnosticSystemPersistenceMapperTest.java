package com.backintro.infrastructure.diagnosticsystem.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.diagnosticsystem.model.aggregate.DiagnosticSystem;

class DiagnosticSystemPersistenceMapperTest {

    private final DiagnosticSystemPersistenceMapper mapper =
            new DiagnosticSystemPersistenceMapper();

    @Test
    void mapsDiagnosticSystemInBothDirectionsWithoutCreatingDomainEvents() {
        DiagnosticSystem original = DiagnosticSystem.register(
                "International Classification of Diseases",
                "ICD",
                "11"
        );

        DiagnosticSystem restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("International Classification of Diseases");
        assertThat(restored.code()).isEqualTo("ICD");
        assertThat(restored.active()).isTrue();
        assertThat(restored.version())
                .isEqualTo("11");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
