package com.backintro.infrastructure.mentalstatusexam.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.mentalstatusexam.usecase.DeleteMentalStatusExamUseCase;
import com.backintro.application.mentalstatusexam.usecase.GetMentalStatusExamByIdUseCase;
import com.backintro.application.mentalstatusexam.usecase.ListMentalStatusExamUseCase;
import com.backintro.application.mentalstatusexam.usecase.RegisterMentalStatusExamUseCase;
import com.backintro.application.mentalstatusexam.usecase.UpdateMentalStatusExamUseCase;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.mentalstatusexam.port.repository.MentalStatusExamRepository;

@Configuration
public class MentalStatusExamBeanConfiguration {

    @Bean
    RegisterMentalStatusExamUseCase registerMentalStatusExamUseCase(
            MentalStatusExamRepository mentalStatusExamRepository,
            EncounterRepository encounterRepository
    ) {
        return new RegisterMentalStatusExamUseCase(
                mentalStatusExamRepository,
                encounterRepository
        );
    }

    @Bean
    GetMentalStatusExamByIdUseCase getMentalStatusExamByIdUseCase(
            MentalStatusExamRepository repository
    ) {
        return new GetMentalStatusExamByIdUseCase(repository);
    }

    @Bean
    ListMentalStatusExamUseCase listMentalStatusExamUseCase(
            MentalStatusExamRepository repository
    ) {
        return new ListMentalStatusExamUseCase(repository);
    }

    @Bean
    UpdateMentalStatusExamUseCase updateMentalStatusExamUseCase(
            MentalStatusExamRepository repository
    ) {
        return new UpdateMentalStatusExamUseCase(repository);
    }

    @Bean
    DeleteMentalStatusExamUseCase deleteMentalStatusExamUseCase(
            MentalStatusExamRepository repository
    ) {
        return new DeleteMentalStatusExamUseCase(repository);
    }
}
