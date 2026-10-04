package com.backintro.application.study.usecase;

import com.backintro.application.study.command.UpdateStudyCommand;
import com.backintro.application.study.dto.StudyResponse;
import com.backintro.application.study.exception.StudyNotFoundApplicationException;
import com.backintro.domain.study.port.repository.StudyRepository;

public class UpdateStudyUseCase {

    private final StudyRepository studyRepository;

    public UpdateStudyUseCase(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public StudyResponse execute(UpdateStudyCommand command) {
        var study = studyRepository.findById(command.id())
                .orElseThrow(() ->
                        new StudyNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        study.update(command.name());

        var updated = studyRepository.save(study);

        return new StudyResponse(
                updated.id().value(),
                updated.name()
        );
    }
}
