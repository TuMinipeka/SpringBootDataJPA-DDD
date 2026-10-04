package com.backintro.infrastructure.documenttype.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.documenttype.model.aggregate.DocumentType;

class DocumentTypePersistenceMapperTest {

    private final DocumentTypePersistenceMapper mapper =
            new DocumentTypePersistenceMapper();

    @Test
    void mapsDocumentTypeInBothDirectionsWithoutCreatingDomainEvents() {
        DocumentType original = DocumentType.register(
                "Citizenship Card",
                "CC"
        );

        DocumentType restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Citizenship Card");
        assertThat(restored.code()).isEqualTo("CC");
        assertThat(restored.active()).isTrue();
        assertThat(restored.domainEvents()).isEmpty();
    }
}
