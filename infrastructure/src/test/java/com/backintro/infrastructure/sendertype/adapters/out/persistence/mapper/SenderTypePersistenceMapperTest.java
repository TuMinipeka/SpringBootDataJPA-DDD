package com.backintro.infrastructure.sendertype.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.sendertype.model.aggregate.SenderType;

class SenderTypePersistenceMapperTest {

    private final SenderTypePersistenceMapper mapper =
            new SenderTypePersistenceMapper();

    @Test
    void mapsSenderTypeInBothDirectionsWithoutCreatingDomainEvents() {
        SenderType original = SenderType.register("Professional");

        SenderType restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.nameType()).isEqualTo("Professional");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
