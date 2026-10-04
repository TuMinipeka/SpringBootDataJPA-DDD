package com.backintro.infrastructure.risklevel.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.risklevel.model.aggregate.RiskLevel;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;
import com.backintro.infrastructure.risklevel.adapters.out.persistence.entity.RiskLevelJpaEntity;

@Component
public class RiskLevelPersistenceMapper {

    public RiskLevel toDomain(RiskLevelJpaEntity entity) {
        return RiskLevel.restore(
                new RiskLevelId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive(),
                entity.getSeverity()
        );
    }

    public RiskLevelJpaEntity toNewEntity(RiskLevel riskLevel) {
        return new RiskLevelJpaEntity(
                riskLevel.id().value(),
                riskLevel.name(),
                riskLevel.code(),
                riskLevel.active(),
                riskLevel.severity()
        );
    }

    public void synchronize(
            RiskLevel riskLevel,
            RiskLevelJpaEntity entity
    ) {
        entity.synchronize(
                riskLevel.name(),
                riskLevel.code(),
                riskLevel.active(),
                riskLevel.severity()
        );
    }
}
