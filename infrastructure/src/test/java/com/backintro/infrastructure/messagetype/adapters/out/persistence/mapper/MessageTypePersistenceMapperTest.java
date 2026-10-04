package com.backintro.infrastructure.messagetype.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.messagetype.model.aggregate.MessageType;

class MessageTypePersistenceMapperTest {

    private final MessageTypePersistenceMapper mapper =
            new MessageTypePersistenceMapper();

    @Test
    void mapsMessageTypeInBothDirectionsWithoutCreatingDomainEvents() {
        MessageType original = MessageType.register("Text");

        MessageType restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.nameType()).isEqualTo("Text");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
