package com.backintro.infrastructure.consenttype.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.consenttype.model.aggregate.ConsentType;
import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;
import com.backintro.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;

@Component
public class ConsentTypePersistenceMapper {

    public ConsentType toDomain(ConsentTypeJpaEntity entity) {
        return ConsentType.restore(
                new ConsentTypeId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive(),
                entity.getDescription()
        );
    }

    public ConsentTypeJpaEntity toNewEntity(
            ConsentType consentType
    ) {
        return new ConsentTypeJpaEntity(
                consentType.id().value(),
                consentType.name(),
                consentType.code(),
                consentType.active(),
                consentType.description()
        );
    }

    public void synchronize(
            ConsentType consentType,
            ConsentTypeJpaEntity entity
    ) {
        entity.synchronize(
                consentType.name(),
                consentType.code(),
                consentType.active(),
                consentType.description()
        );
    }
}
