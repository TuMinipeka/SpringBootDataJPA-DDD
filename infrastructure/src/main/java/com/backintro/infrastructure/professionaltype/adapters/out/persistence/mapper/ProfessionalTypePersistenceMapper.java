package com.backintro.infrastructure.professionaltype.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.professionaltype.model.aggregate.ProfessionalType;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.backintro.infrastructure.professionaltype.adapters.out.persistence.entity.ProfessionalTypeJpaEntity;

@Component
public class ProfessionalTypePersistenceMapper {

    public ProfessionalType toDomain(ProfessionalTypeJpaEntity entity) {
        return ProfessionalType.restore(
                new ProfessionalTypeId(entity.getId()),
                entity.getName()
        );
    }

    public ProfessionalTypeJpaEntity toNewEntity(
            ProfessionalType professionalType
    ) {
        return new ProfessionalTypeJpaEntity(
                professionalType.id().value(),
                professionalType.name()
        );
    }

    public void synchronize(
            ProfessionalType professionalType,
            ProfessionalTypeJpaEntity entity
    ) {
        entity.synchronize(professionalType.name());
    }
}
