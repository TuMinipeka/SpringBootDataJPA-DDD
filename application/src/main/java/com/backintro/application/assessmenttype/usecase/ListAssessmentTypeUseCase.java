package com.backintro.application.assessmenttype.usecase;

import java.util.List;

import com.backintro.application.assessmenttype.dto.AssessmentTypeResponse;
import com.backintro.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class ListAssessmentTypeUseCase {

    private final AssessmentTypeRepository assessmentTypeRepository;

    public ListAssessmentTypeUseCase(
            AssessmentTypeRepository assessmentTypeRepository
    ) {
        this.assessmentTypeRepository = assessmentTypeRepository;
    }

    public List<AssessmentTypeResponse> execute() {
        return assessmentTypeRepository.findAll()
                .stream()
                .map(assessmentType ->
                        new AssessmentTypeResponse(
                                assessmentType.id().value(),
                                assessmentType.name(),
                                assessmentType.code(),
                                assessmentType.description()
                        )
                )
                .toList();
    }
}
