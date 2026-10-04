package com.backintro.infrastructure.professionalstudy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.professionalstudy.usecase.DeleteProfessionalStudyUseCase;
import com.backintro.application.professionalstudy.usecase.GetProfessionalStudyByIdUseCase;
import com.backintro.application.professionalstudy.usecase.ListProfessionalStudyUseCase;
import com.backintro.application.professionalstudy.usecase.RegisterProfessionalStudyUseCase;
import com.backintro.application.professionalstudy.usecase.UpdateProfessionalStudyUseCase;
import com.backintro.domain.country.port.repository.CountryRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.professionalstudy.port.repository.ProfessionalStudyRepository;
import com.backintro.domain.study.port.repository.StudyRepository;

@Configuration
public class ProfessionalStudyBeanConfiguration {

    @Bean
    RegisterProfessionalStudyUseCase registerProfessionalStudyUseCase(
            ProfessionalStudyRepository professionalStudyRepository,
            StudyRepository studyRepository,
            ProfessionalRepository professionalRepository,
            CountryRepository countryRepository
    ) {
        return new RegisterProfessionalStudyUseCase(
                professionalStudyRepository,
                studyRepository,
                professionalRepository,
                countryRepository
        );
    }

    @Bean
    GetProfessionalStudyByIdUseCase getProfessionalStudyByIdUseCase(
            ProfessionalStudyRepository repository
    ) {
        return new GetProfessionalStudyByIdUseCase(repository);
    }

    @Bean
    ListProfessionalStudyUseCase listProfessionalStudyUseCase(
            ProfessionalStudyRepository repository
    ) {
        return new ListProfessionalStudyUseCase(repository);
    }

    @Bean
    UpdateProfessionalStudyUseCase updateProfessionalStudyUseCase(
            ProfessionalStudyRepository repository
    ) {
        return new UpdateProfessionalStudyUseCase(repository);
    }

    @Bean
    DeleteProfessionalStudyUseCase deleteProfessionalStudyUseCase(
            ProfessionalStudyRepository repository
    ) {
        return new DeleteProfessionalStudyUseCase(repository);
    }
}
