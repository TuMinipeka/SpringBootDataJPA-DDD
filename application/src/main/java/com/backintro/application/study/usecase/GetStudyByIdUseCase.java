package com.backintro.application.study.usecase;

import com.backintro.application.study.dto.StudyResponse;
import com.backintro.application.study.exception.StudyNotFoundApplicationException;
import com.backintro.domain.study.model.valueobject.StudyId;
import com.backintro.domain.study.port.repository.StudyRepository;

public class GetStudyByIdUseCase {

    private final StudyRepository studyRepository;

    public GetStudyByIdUseCase(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public StudyResponse execute(StudyId id) {
        var study = studyRepository.findById(id)
                .orElseThrow(() ->
                        new StudyNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new StudyResponse(
                study.id().value(),
                study.name()
        );
    }
}
