package com.backintro.infrastructure.professionaltype.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.professionaltype.model.aggregate.ProfessionalType;

class ProfessionalTypePersistenceMapperTest {

    private final ProfessionalTypePersistenceMapper mapper =
            new ProfessionalTypePersistenceMapper();

    @Test
    void mapsProfessionalTypeInBothDirectionsWithoutCreatingDomainEvents() {
        ProfessionalType original = ProfessionalType.register("Psychologist");

        ProfessionalType restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Psychologist");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
