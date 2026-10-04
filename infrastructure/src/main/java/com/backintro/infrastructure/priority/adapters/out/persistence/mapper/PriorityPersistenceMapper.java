package com.backintro.infrastructure.priority.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.priority.model.aggregate.Priority;
import com.backintro.domain.priority.model.valueobject.PriorityId;
import com.backintro.infrastructure.priority.adapters.out.persistence.entity.PriorityJpaEntity;

@Component
public class PriorityPersistenceMapper {

    public Priority toDomain(PriorityJpaEntity entity) {
        return Priority.restore(
                new PriorityId(entity.getId()),
                entity.getNamePriority()
        );
    }

    public PriorityJpaEntity toNewEntity(
            Priority priority
    ) {
        return new PriorityJpaEntity(
                priority.id().value(),
                priority.namePriority()
        );
    }

    public void synchronize(
            Priority priority,
            PriorityJpaEntity entity
    ) {
        entity.synchronize(priority.namePriority());
    }
}
