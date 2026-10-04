package com.backintro.application.study.usecase;

import java.time.LocalDateTime;

import com.backintro.application.study.exception.StudyNotFoundApplicationException;
import com.backintro.domain.study.event.StudyDeletedEvent;
import com.backintro.domain.study.model.valueobject.StudyId;
import com.backintro.domain.study.port.repository.StudyRepository;

public class DeleteStudyUseCase {

    private final StudyRepository studyRepository;

    public DeleteStudyUseCase(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public StudyDeletedEvent execute(StudyId id) {
        var study = studyRepository.findById(id)
                .orElseThrow(() ->
                        new StudyNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        studyRepository.delete(study);

        return new StudyDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
