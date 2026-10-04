package com.backintro.infrastructure.study.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

import com.backintro.domain.study.model.aggregate.Study;

class StudyPersistenceMapperTest {

    private final StudyPersistenceMapper mapper =
            new StudyPersistenceMapper();

    @Test
    void mapsStudyInBothDirectionsWithoutCreatingDomainEvents() {
        Study original = Study.register("Psychology");

        Study restored = mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.name()).isEqualTo("Psychology");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
