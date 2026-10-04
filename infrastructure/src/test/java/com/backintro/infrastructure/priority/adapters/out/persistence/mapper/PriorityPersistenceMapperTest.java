package com.backintro.infrastructure.priority.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.priority.model.aggregate.Priority;

class PriorityPersistenceMapperTest {

    private final PriorityPersistenceMapper mapper =
            new PriorityPersistenceMapper();

    @Test
    void mapsPriorityInBothDirectionsWithoutCreatingDomainEvents() {
        Priority original = Priority.register("High");

        Priority restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.namePriority()).isEqualTo("High");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
