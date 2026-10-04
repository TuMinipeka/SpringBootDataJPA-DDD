package com.backintro.infrastructure.study.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.study.model.aggregate.Study;
import com.backintro.domain.study.model.valueobject.StudyId;
import com.backintro.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;

@Component
public class StudyPersistenceMapper {

    public Study toDomain(StudyJpaEntity entity) {
        return Study.restore(
                new StudyId(entity.getId()),
                entity.getName()
        );
    }

    public StudyJpaEntity toNewEntity(Study study) {
        return new StudyJpaEntity(
                study.id().value(),
                study.name()
        );
    }

    public void synchronize(Study study, StudyJpaEntity entity) {
        entity.synchronize(study.name());
    }
}
