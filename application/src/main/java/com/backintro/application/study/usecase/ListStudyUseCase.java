package com.backintro.application.study.usecase;

import java.util.List;

import com.backintro.application.study.dto.StudyResponse;
import com.backintro.domain.study.port.repository.StudyRepository;

public class ListStudyUseCase {

    private final StudyRepository studyRepository;

    public ListStudyUseCase(StudyRepository studyRepository) {
        this.studyRepository = studyRepository;
    }

    public List<StudyResponse> execute() {
        return studyRepository.findAll()
                .stream()
                .map(study ->
                        new StudyResponse(
                                study.id().value(),
                                study.name()
                        )
                )
                .toList();
    }
}
