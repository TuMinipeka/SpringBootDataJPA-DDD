package com.backintro.domain.professionalstudy.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.backintro.domain.study.model.valueobject.StudyId;

public interface ProfessionalStudyRepository {

    ProfessionalStudy save(ProfessionalStudy professionalStudy);

    Optional<ProfessionalStudy> findById(ProfessionalStudyId id);

    List<ProfessionalStudy> findAll();

    boolean existsByProfessionalIdAndStudyIdAndTitle(
            ProfessionalId professionalId,
            StudyId studyId,
            String title
    );

    void delete(ProfessionalStudy professionalStudy);
}
