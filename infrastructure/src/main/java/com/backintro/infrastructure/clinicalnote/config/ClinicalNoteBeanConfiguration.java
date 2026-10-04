package com.backintro.infrastructure.clinicalnote.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.clinicalnote.usecase.DeleteClinicalNoteUseCase;
import com.backintro.application.clinicalnote.usecase.GetClinicalNoteByIdUseCase;
import com.backintro.application.clinicalnote.usecase.ListClinicalNoteUseCase;
import com.backintro.application.clinicalnote.usecase.RegisterClinicalNoteUseCase;
import com.backintro.application.clinicalnote.usecase.UpdateClinicalNoteUseCase;
import com.backintro.domain.clinicalnote.port.repository.ClinicalNoteRepository;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

@Configuration
public class ClinicalNoteBeanConfiguration {

    @Bean
    RegisterClinicalNoteUseCase registerClinicalNoteUseCase(
            ClinicalNoteRepository clinicalNoteRepository,
            EncounterRepository encounterRepository,
            ProfessionalRepository professionalRepository
    ) {
        return new RegisterClinicalNoteUseCase(
                clinicalNoteRepository,
                encounterRepository,
                professionalRepository
        );
    }

    @Bean
    GetClinicalNoteByIdUseCase getClinicalNoteByIdUseCase(
            ClinicalNoteRepository repository
    ) {
        return new GetClinicalNoteByIdUseCase(repository);
    }

    @Bean
    ListClinicalNoteUseCase listClinicalNoteUseCase(
            ClinicalNoteRepository repository
    ) {
        return new ListClinicalNoteUseCase(repository);
    }

    @Bean
    UpdateClinicalNoteUseCase updateClinicalNoteUseCase(
            ClinicalNoteRepository repository
    ) {
        return new UpdateClinicalNoteUseCase(repository);
    }

    @Bean
    DeleteClinicalNoteUseCase deleteClinicalNoteUseCase(
            ClinicalNoteRepository repository
    ) {
        return new DeleteClinicalNoteUseCase(repository);
    }
}
