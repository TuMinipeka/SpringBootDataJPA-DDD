package com.backintro.infrastructure.professionalstudy.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.backintro.domain.study.model.valueobject.StudyId;
import com.backintro.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;

@Component
public class ProfessionalStudyPersistenceMapper {

    public ProfessionalStudy toDomain(ProfessionalStudyJpaEntity entity) {
        return ProfessionalStudy.restore(
                new ProfessionalStudyId(entity.getId()),
                new StudyId(entity.getStudyId()),
                new ProfessionalId(entity.getProfessionalId()),
                entity.getTitle(),
                entity.getUniversity(),
                entity.isValid(),
                entity.getResolutionNumber(),
                entity.getCountryId() == null
                        ? null
                        : new CountryId(entity.getCountryId())
        );
    }

    public ProfessionalStudyJpaEntity toNewEntity(
            ProfessionalStudy professionalStudy
    ) {
        return new ProfessionalStudyJpaEntity(
                professionalStudy.id().value(),
                professionalStudy.studyId().value(),
                professionalStudy.professionalId().value(),
                professionalStudy.title(),
                professionalStudy.university(),
                professionalStudy.valid(),
                professionalStudy.resolutionNumber(),
                professionalStudy.countryId() == null
                        ? null
                        : professionalStudy.countryId().value()
        );
    }

    public void synchronize(
            ProfessionalStudy professionalStudy,
            ProfessionalStudyJpaEntity entity
    ) {
        entity.synchronize(
                professionalStudy.title(),
                professionalStudy.university(),
                professionalStudy.valid(),
                professionalStudy.resolutionNumber()
        );
    }
}
