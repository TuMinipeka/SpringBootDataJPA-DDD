package com.backintro.infrastructure.consenttype.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.consenttype.model.aggregate.ConsentType;

class ConsentTypePersistenceMapperTest {

    private final ConsentTypePersistenceMapper mapper =
            new ConsentTypePersistenceMapper();

    @Test
    void mapsConsentTypeInBothDirectionsWithoutCreatingDomainEvents() {
        ConsentType original = ConsentType.register(
                "Informed consent",
                "INFORMED",
                "Informed consent documentation"
        );

        ConsentType restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Informed consent");
        assertThat(restored.code()).isEqualTo("INFORMED");
        assertThat(restored.active()).isTrue();
        assertThat(restored.description())
                .isEqualTo("Informed consent documentation");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
