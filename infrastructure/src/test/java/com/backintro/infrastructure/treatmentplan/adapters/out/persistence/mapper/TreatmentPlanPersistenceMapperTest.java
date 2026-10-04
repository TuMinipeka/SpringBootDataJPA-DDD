package com.backintro.infrastructure.treatmentplan.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

class TreatmentPlanPersistenceMapperTest {

    private final TreatmentPlanPersistenceMapper mapper =
            new TreatmentPlanPersistenceMapper();

    @Test
    void mapsTreatmentPlanInBothDirectionsWithoutCreatingDomainEvents() {
        EncounterId encounterId = new EncounterId(UUID.randomUUID());
        ProfessionalId professionalId =
                new ProfessionalId(UUID.randomUUID());
        TreatmentStatusId statusId =
                new TreatmentStatusId(UUID.randomUUID());
        TreatmentPlan original = TreatmentPlan.register(
                encounterId,
                professionalId,
                "Cognitive behavioral plan",
                "Weekly sessions",
                LocalDate.of(2026, 10, 4),
                statusId
        );

        TreatmentPlan restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.encounterId()).isEqualTo(encounterId);
        assertThat(restored.professionalId()).isEqualTo(professionalId);
        assertThat(restored.title()).isEqualTo("Cognitive behavioral plan");
        assertThat(restored.description()).isEqualTo("Weekly sessions");
        assertThat(restored.startDate()).isEqualTo(LocalDate.of(2026, 10, 4));
        assertThat(restored.endDate()).isNull();
        assertThat(restored.statusId()).isEqualTo(statusId);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
