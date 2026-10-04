package com.backintro.infrastructure.assessmenttype.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.assessmenttype.usecase.DeleteAssessmentTypeUseCase;
import com.backintro.application.assessmenttype.usecase.GetAssessmentTypeByIdUseCase;
import com.backintro.application.assessmenttype.usecase.ListAssessmentTypeUseCase;
import com.backintro.application.assessmenttype.usecase.RegisterAssessmentTypeUseCase;
import com.backintro.application.assessmenttype.usecase.UpdateAssessmentTypeUseCase;
import com.backintro.domain.assessmenttype.port.repository.AssessmentTypeRepository;

@Configuration
public class AssessmentTypeBeanConfiguration {

    @Bean
    RegisterAssessmentTypeUseCase registerAssessmentTypeUseCase(
            AssessmentTypeRepository repository
    ) {
        return new RegisterAssessmentTypeUseCase(repository);
    }

    @Bean
    GetAssessmentTypeByIdUseCase getAssessmentTypeByIdUseCase(
            AssessmentTypeRepository repository
    ) {
        return new GetAssessmentTypeByIdUseCase(repository);
    }

    @Bean
    ListAssessmentTypeUseCase listAssessmentTypeUseCase(
            AssessmentTypeRepository repository
    ) {
        return new ListAssessmentTypeUseCase(repository);
    }

    @Bean
    UpdateAssessmentTypeUseCase updateAssessmentTypeUseCase(
            AssessmentTypeRepository repository
    ) {
        return new UpdateAssessmentTypeUseCase(repository);
    }

    @Bean
    DeleteAssessmentTypeUseCase deleteAssessmentTypeUseCase(
            AssessmentTypeRepository repository
    ) {
        return new DeleteAssessmentTypeUseCase(repository);
    }
}
