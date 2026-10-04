package com.backintro.application.assessmenttype.usecase;

import com.backintro.application.assessmenttype.dto.AssessmentTypeResponse;
import com.backintro.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import com.backintro.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.backintro.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class GetAssessmentTypeByIdUseCase {

    private final AssessmentTypeRepository assessmentTypeRepository;

    public GetAssessmentTypeByIdUseCase(
            AssessmentTypeRepository assessmentTypeRepository
    ) {
        this.assessmentTypeRepository = assessmentTypeRepository;
    }

    public AssessmentTypeResponse execute(AssessmentTypeId id) {
        var assessmentType = assessmentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new AssessmentTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new AssessmentTypeResponse(
                assessmentType.id().value(),
                assessmentType.name(),
                assessmentType.code(),
                assessmentType.description()
        );
    }
}
