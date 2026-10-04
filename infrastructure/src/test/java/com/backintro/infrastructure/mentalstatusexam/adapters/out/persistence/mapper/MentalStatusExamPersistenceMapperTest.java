package com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.mentalstatusexam.model.aggregate.MentalStatusExam;

class MentalStatusExamPersistenceMapperTest {

    private final MentalStatusExamPersistenceMapper mapper =
            new MentalStatusExamPersistenceMapper();

    @Test
    void mapsMentalStatusExamInBothDirectionsWithoutCreatingDomainEvents() {
        EncounterId encounterId = new EncounterId(UUID.randomUUID());
        MentalStatusExam original = MentalStatusExam.register(
                encounterId,
                "Neat",
                "Cooperative",
                "Open",
                "Alert",
                "Oriented",
                "Sustained",
                "Intact",
                "Clear",
                "Euthymic",
                "Congruent",
                "Logical",
                "No delusions",
                "No alterations",
                "Preserved",
                "Adequate",
                "Normal",
                "No additional observations"
        );

        MentalStatusExam restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.encounterId()).isEqualTo(encounterId);
        assertThat(restored.appearance()).isEqualTo("Neat");
        assertThat(restored.behavior()).isEqualTo("Cooperative");
        assertThat(restored.attitude()).isEqualTo("Open");
        assertThat(restored.consciousness()).isEqualTo("Alert");
        assertThat(restored.orientation()).isEqualTo("Oriented");
        assertThat(restored.attention()).isEqualTo("Sustained");
        assertThat(restored.memory()).isEqualTo("Intact");
        assertThat(restored.speech()).isEqualTo("Clear");
        assertThat(restored.mood()).isEqualTo("Euthymic");
        assertThat(restored.affect()).isEqualTo("Congruent");
        assertThat(restored.thoughtProcess()).isEqualTo("Logical");
        assertThat(restored.thoughtContent()).isEqualTo("No delusions");
        assertThat(restored.perception()).isEqualTo("No alterations");
        assertThat(restored.judgment()).isEqualTo("Preserved");
        assertThat(restored.insight()).isEqualTo("Adequate");
        assertThat(restored.psychomotorActivity()).isEqualTo("Normal");
        assertThat(restored.observations())
                .isEqualTo("No additional observations");
        assertThat(restored.domainEvents()).isEmpty();
    }
}
