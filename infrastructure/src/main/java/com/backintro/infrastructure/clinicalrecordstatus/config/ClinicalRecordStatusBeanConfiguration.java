package com.backintro.infrastructure.clinicalrecordstatus.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.clinicalrecordstatus.usecase.DeleteClinicalRecordStatusUseCase;
import com.backintro.application.clinicalrecordstatus.usecase.GetClinicalRecordStatusByIdUseCase;
import com.backintro.application.clinicalrecordstatus.usecase.ListClinicalRecordStatusUseCase;
import com.backintro.application.clinicalrecordstatus.usecase.RegisterClinicalRecordStatusUseCase;
import com.backintro.application.clinicalrecordstatus.usecase.UpdateClinicalRecordStatusUseCase;
import com.backintro.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;

@Configuration
public class ClinicalRecordStatusBeanConfiguration {

    @Bean
    RegisterClinicalRecordStatusUseCase registerClinicalRecordStatusUseCase(
            ClinicalRecordStatusRepository repository
    ) {
        return new RegisterClinicalRecordStatusUseCase(repository);
    }

    @Bean
    GetClinicalRecordStatusByIdUseCase getClinicalRecordStatusByIdUseCase(
            ClinicalRecordStatusRepository repository
    ) {
        return new GetClinicalRecordStatusByIdUseCase(repository);
    }

    @Bean
    ListClinicalRecordStatusUseCase listClinicalRecordStatusUseCase(
            ClinicalRecordStatusRepository repository
    ) {
        return new ListClinicalRecordStatusUseCase(repository);
    }

    @Bean
    UpdateClinicalRecordStatusUseCase updateClinicalRecordStatusUseCase(
            ClinicalRecordStatusRepository repository
    ) {
        return new UpdateClinicalRecordStatusUseCase(repository);
    }

    @Bean
    DeleteClinicalRecordStatusUseCase deleteClinicalRecordStatusUseCase(
            ClinicalRecordStatusRepository repository
    ) {
        return new DeleteClinicalRecordStatusUseCase(repository);
    }
}
