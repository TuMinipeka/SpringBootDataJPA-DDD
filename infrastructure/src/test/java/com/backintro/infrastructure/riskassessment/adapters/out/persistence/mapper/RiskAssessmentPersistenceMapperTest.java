package com.backintro.infrastructure.riskassessment.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.riskassessment.model.aggregate.RiskAssessment;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

class RiskAssessmentPersistenceMapperTest {

    private final RiskAssessmentPersistenceMapper mapper =
            new RiskAssessmentPersistenceMapper();

    @Test
    void mapsRiskAssessmentInBothDirectionsWithoutCreatingDomainEvents() {
        EncounterId encounterId = new EncounterId(UUID.randomUUID());
        RiskLevelId riskLevelId = new RiskLevelId(UUID.randomUUID());
        ProfessionalId assessedBy = new ProfessionalId(UUID.randomUUID());
        RiskAssessment original = RiskAssessment.register(
                encounterId,
                riskLevelId,
                true,
                false,
                false,
                true,
                false,
                "Previous attempts",
                "Family support",
                "Safety plan",
                "Requires monitoring",
                assessedBy
        );

        RiskAssessment restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.encounterId()).isEqualTo(encounterId);
        assertThat(restored.riskLevelId()).isEqualTo(riskLevelId);
        assertThat(restored.suicidalIdeation()).isTrue();
        assertThat(restored.suicidePlan()).isFalse();
        assertThat(restored.selfHarm()).isTrue();
        assertThat(restored.riskFactors()).isEqualTo("Previous attempts");
        assertThat(restored.protectiveFactors()).isEqualTo("Family support");
        assertThat(restored.clinicalActions()).isEqualTo("Safety plan");
        assertThat(restored.assessedAt()).isEqualTo(original.assessedAt());
        assertThat(restored.assessedBy()).isEqualTo(assessedBy);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
