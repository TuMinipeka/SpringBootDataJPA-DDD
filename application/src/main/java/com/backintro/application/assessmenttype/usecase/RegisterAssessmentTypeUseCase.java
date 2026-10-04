package com.backintro.application.assessmenttype.usecase;

import com.backintro.application.assessmenttype.command.RegisterAssessmentTypeCommand;
import com.backintro.application.assessmenttype.dto.AssessmentTypeResponse;
import com.backintro.domain.assessmenttype.model.aggregate.AssessmentType;
import com.backintro.domain.assessmenttype.port.repository.AssessmentTypeRepository;

public class RegisterAssessmentTypeUseCase {

    private final AssessmentTypeRepository assessmentTypeRepository;

    public RegisterAssessmentTypeUseCase(
            AssessmentTypeRepository assessmentTypeRepository
    ) {
        this.assessmentTypeRepository = assessmentTypeRepository;
    }

    public AssessmentTypeResponse execute(
            RegisterAssessmentTypeCommand command
    ) {
        AssessmentType assessmentType = AssessmentType.register(
                command.name(),
                command.code(),
                command.description()
        );

        AssessmentType saved =
                assessmentTypeRepository.save(assessmentType);

        return new AssessmentTypeResponse(
                saved.id().value(),
                saved.name(),
                saved.code(),
                saved.description()
        );
    }
}
