package com.backintro.infrastructure.relationshiptype.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.relationshiptype.model.aggregate.RelationshipType;

class RelationshipTypePersistenceMapperTest {

    private final RelationshipTypePersistenceMapper mapper =
            new RelationshipTypePersistenceMapper();

    @Test
    void mapsRelationshipTypeInBothDirectionsWithoutCreatingDomainEvents() {
        RelationshipType original = RelationshipType.register("Parent");

        RelationshipType restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.description()).isEqualTo("Parent");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
