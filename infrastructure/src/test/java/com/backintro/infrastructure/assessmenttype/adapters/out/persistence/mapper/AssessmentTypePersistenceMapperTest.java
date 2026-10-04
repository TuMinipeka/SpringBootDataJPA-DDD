package com.backintro.infrastructure.assessmenttype.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.assessmenttype.model.aggregate.AssessmentType;

class AssessmentTypePersistenceMapperTest {

    private final AssessmentTypePersistenceMapper mapper =
            new AssessmentTypePersistenceMapper();

    @Test
    void mapsAssessmentTypeInBothDirectionsWithoutCreatingDomainEvents() {
        AssessmentType original = AssessmentType.register(
                "Psychological",
                "PSY",
                "Psychological assessment"
        );

        AssessmentType restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Psychological");
        assertThat(restored.code()).isEqualTo("PSY");
        assertThat(restored.active()).isTrue();
        assertThat(restored.description())
                .isEqualTo("Psychological assessment");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
