package com.backintro.infrastructure.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.study.usecase.DeleteStudyUseCase;
import com.backintro.application.study.usecase.GetStudyByIdUseCase;
import com.backintro.application.study.usecase.ListStudyUseCase;
import com.backintro.application.study.usecase.RegisterStudyUseCase;
import com.backintro.application.study.usecase.UpdateStudyUseCase;
import com.backintro.domain.study.port.repository.StudyRepository;

@Configuration
public class StudyBeanConfiguration {

    @Bean
    RegisterStudyUseCase registerStudyUseCase(StudyRepository repository) {
        return new RegisterStudyUseCase(repository);
    }

    @Bean
    GetStudyByIdUseCase getStudyByIdUseCase(StudyRepository repository) {
        return new GetStudyByIdUseCase(repository);
    }

    @Bean
    ListStudyUseCase listStudyUseCase(StudyRepository repository) {
        return new ListStudyUseCase(repository);
    }

    @Bean
    UpdateStudyUseCase updateStudyUseCase(StudyRepository repository) {
        return new UpdateStudyUseCase(repository);
    }

    @Bean
    DeleteStudyUseCase deleteStudyUseCase(StudyRepository repository) {
        return new DeleteStudyUseCase(repository);
    }
}
