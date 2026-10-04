package com.backintro.infrastructure.assessmenttype.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.assessmenttype.model.aggregate.AssessmentType;
import com.backintro.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.backintro.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;

@Component
public class AssessmentTypePersistenceMapper {

    public AssessmentType toDomain(AssessmentTypeJpaEntity entity) {
        return AssessmentType.restore(
                new AssessmentTypeId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive(),
                entity.getDescription()
        );
    }

    public AssessmentTypeJpaEntity toNewEntity(
            AssessmentType assessmentType
    ) {
        return new AssessmentTypeJpaEntity(
                assessmentType.id().value(),
                assessmentType.name(),
                assessmentType.code(),
                assessmentType.active(),
                assessmentType.description()
        );
    }

    public void synchronize(
            AssessmentType assessmentType,
            AssessmentTypeJpaEntity entity
    ) {
        entity.synchronize(
                assessmentType.name(),
                assessmentType.code(),
                assessmentType.active(),
                assessmentType.description()
        );
    }
}
