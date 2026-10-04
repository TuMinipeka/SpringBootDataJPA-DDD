package com.backintro.application.assessmenttype.usecase;

import com.backintro.application.assessmenttype.command.UpdateAssessmentTypeCommand;
import com.backintro.application.assessmenttype.dto.AssessmentTypeResponse;
import com.backintro.application.assessmenttype.exception.AssessmentTypeNotFoundApplicationException;
import com.backintro.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class UpdateAssessmentTypeUseCase {

    private final AssessmentTypeRepository assessmentTypeRepository;

    public UpdateAssessmentTypeUseCase(
            AssessmentTypeRepository assessmentTypeRepository
    ) {
        this.assessmentTypeRepository = assessmentTypeRepository;
    }

    public AssessmentTypeResponse execute(
            UpdateAssessmentTypeCommand command
    ) {
        var assessmentType = assessmentTypeRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new AssessmentTypeNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        assessmentType.update(
                command.name(),
                command.code(),
                command.description()
        );

        var updated = assessmentTypeRepository.save(assessmentType);

        return new AssessmentTypeResponse(
                updated.id().value(),
                updated.name(),
                updated.code(),
                updated.description()
        );
    }
}
