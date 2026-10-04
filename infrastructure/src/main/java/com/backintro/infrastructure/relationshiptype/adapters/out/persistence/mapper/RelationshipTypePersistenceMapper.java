package com.backintro.infrastructure.relationshiptype.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.relationshiptype.model.aggregate.RelationshipType;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.backintro.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;

@Component
public class RelationshipTypePersistenceMapper {

    public RelationshipType toDomain(RelationshipTypeJpaEntity entity) {
        return RelationshipType.restore(
                new RelationshipTypeId(entity.getId()),
                entity.getDescription()
        );
    }

    public RelationshipTypeJpaEntity toNewEntity(
            RelationshipType relationshipType
    ) {
        return new RelationshipTypeJpaEntity(
                relationshipType.id().value(),
                relationshipType.description()
        );
    }

    public void synchronize(
            RelationshipType relationshipType,
            RelationshipTypeJpaEntity entity
    ) {
        entity.synchronize(relationshipType.description());
    }
}
