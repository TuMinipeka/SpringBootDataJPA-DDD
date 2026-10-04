package com.backintro.infrastructure.riskassessment.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.riskassessment.model.aggregate.RiskAssessment;
import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;
import com.backintro.infrastructure.riskassessment.adapters.out.persistence.entity.RiskAssessmentJpaEntity;

@Component
public class RiskAssessmentPersistenceMapper {

    public RiskAssessment toDomain(RiskAssessmentJpaEntity entity) {
        return RiskAssessment.restore(
                new RiskAssessmentId(entity.getId()),
                new EncounterId(entity.getEncounterId()),
                new RiskLevelId(entity.getRiskLevelId()),
                entity.isSuicidalIdeation(),
                entity.isSuicidePlan(),
                entity.isSuicideIntent(),
                entity.isSelfHarm(),
                entity.isHarmToOthers(),
                entity.getRiskFactors(),
                entity.getProtectiveFactors(),
                entity.getClinicalActions(),
                entity.getObservations(),
                entity.getAssessedAt(),
                new ProfessionalId(entity.getAssessedBy())
        );
    }

    public RiskAssessmentJpaEntity toNewEntity(
            RiskAssessment riskAssessment
    ) {
        return new RiskAssessmentJpaEntity(
                riskAssessment.id().value(),
                riskAssessment.encounterId().value(),
                riskAssessment.riskLevelId().value(),
                riskAssessment.suicidalIdeation(),
                riskAssessment.suicidePlan(),
                riskAssessment.suicideIntent(),
                riskAssessment.selfHarm(),
                riskAssessment.harmToOthers(),
                riskAssessment.riskFactors(),
                riskAssessment.protectiveFactors(),
                riskAssessment.clinicalActions(),
                riskAssessment.observations(),
                riskAssessment.assessedAt(),
                riskAssessment.assessedBy().value()
        );
    }

    public void synchronize(
            RiskAssessment riskAssessment,
            RiskAssessmentJpaEntity entity
    ) {
        entity.synchronize(
                riskAssessment.riskLevelId().value(),
                riskAssessment.suicidalIdeation(),
                riskAssessment.suicidePlan(),
                riskAssessment.suicideIntent(),
                riskAssessment.selfHarm(),
                riskAssessment.harmToOthers(),
                riskAssessment.riskFactors(),
                riskAssessment.protectiveFactors(),
                riskAssessment.clinicalActions(),
                riskAssessment.observations()
        );
    }
}
