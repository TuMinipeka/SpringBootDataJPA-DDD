package com.backintro.application.study.usecase;

import com.backintro.application.study.command.RegisterStudyCommand;
import com.backintro.application.study.dto.StudyResponse;
import com.backintro.domain.study.model.aggregate.Study;
import com.backintro.domain.study.port.repository.StudyRepository;

public class RegisterStudyUseCase {

    private final StudyRepository studyRepository;

    public RegisterStudyUseCase(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public StudyResponse execute(RegisterStudyCommand command) {
        Study study = Study.register(command.name());

        Study saved = studyRepository.save(study);

        return new StudyResponse(
                saved.id().value(),
                saved.name()
        );
    }
}
