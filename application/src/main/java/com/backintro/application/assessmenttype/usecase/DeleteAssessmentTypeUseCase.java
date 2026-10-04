package com.backintro.application.assessmenttype.usecase;

import java.time.LocalDateTime;

import com.backintro.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import com.backintro.domain.assessmenttype.event.AssessmentTypeDeletedEvent;
import com.backintro.domain.assessmenttype.model.valueobject.AssessmentTypeId;
import com.backintro.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class DeleteAssessmentTypeUseCase {

    private final AssessmentTypeRepository assessmentTypeRepository;

    public DeleteAssessmentTypeUseCase(
            AssessmentTypeRepository assessmentTypeRepository
    ) {
        this.assessmentTypeRepository = assessmentTypeRepository;
    }

    public AssessmentTypeDeletedEvent execute(AssessmentTypeId id) {
        var assessmentType = assessmentTypeRepository.findById(id)
                .orElseThrow(() ->
                        new AssessmentTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        assessmentTypeRepository.delete(assessmentType);

        return new AssessmentTypeDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
