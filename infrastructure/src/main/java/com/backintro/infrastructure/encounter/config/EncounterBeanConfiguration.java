package com.backintro.infrastructure.encounter.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.encounter.usecase.DeleteEncounterUseCase;
import com.backintro.application.encounter.usecase.GetEncounterByIdUseCase;
import com.backintro.application.encounter.usecase.ListEncounterUseCase;
import com.backintro.application.encounter.usecase.RegisterEncounterUseCase;
import com.backintro.application.encounter.usecase.UpdateEncounterUseCase;
import com.backintro.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import com.backintro.domain.encounter.port.repository.EncounterRepository;
import com.backintro.domain.encountermodality.port.repository.EncounterModalityRepository;
import com.backintro.domain.encounterstatus.port.repository.EncounterStatusRepository;
import com.backintro.domain.encountertype.port.repository.EncounterTypeRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

@Configuration
public class EncounterBeanConfiguration {

    @Bean
    RegisterEncounterUseCase registerEncounterUseCase(
            EncounterRepository encounterRepository,
            ClinicalRecordRepository clinicalRecordRepository,
            ProfessionalRepository professionalRepository,
            EncounterTypeRepository encounterTypeRepository,
            EncounterModalityRepository modalityRepository,
            EncounterStatusRepository statusRepository
    ) {
        return new RegisterEncounterUseCase(
                encounterRepository,
                clinicalRecordRepository,
                professionalRepository,
                encounterTypeRepository,
                modalityRepository,
                statusRepository
        );
    }

    @Bean
    GetEncounterByIdUseCase getEncounterByIdUseCase(
            EncounterRepository repository
    ) {
        return new GetEncounterByIdUseCase(repository);
    }

    @Bean
    ListEncounterUseCase listEncounterUseCase(
            EncounterRepository repository
    ) {
        return new ListEncounterUseCase(repository);
    }

    @Bean
    UpdateEncounterUseCase updateEncounterUseCase(
            EncounterRepository encounterRepository,
            EncounterStatusRepository statusRepository
    ) {
        return new UpdateEncounterUseCase(
                encounterRepository,
                statusRepository
        );
    }

    @Bean
    DeleteEncounterUseCase deleteEncounterUseCase(
            EncounterRepository repository
    ) {
        return new DeleteEncounterUseCase(repository);
    }
}
