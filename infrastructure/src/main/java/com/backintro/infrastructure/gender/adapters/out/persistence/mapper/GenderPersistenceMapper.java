package com.backintro.infrastructure.gender.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.gender.model.aggregate.Gender;
import com.backintro.domain.gender.model.valueobject.GenderId;
import com.backintro.infrastructure.gender.adapters.out.persistence.entity.GenderJpaEntity;

@Component
public class GenderPersistenceMapper {

    public Gender toDomain(GenderJpaEntity entity) {
        return Gender.restore(
                new GenderId(entity.getId()),
                entity.getDescription()
        );
    }

    public GenderJpaEntity toNewEntity(Gender gender) {
        return new GenderJpaEntity(
                gender.id().value(),
                gender.description()
        );
    }

    public void synchronize(Gender gender, GenderJpaEntity entity) {
        entity.synchronize(gender.description());
    }
}
