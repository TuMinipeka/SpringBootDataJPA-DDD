package com.backintro.infrastructure.clinicalrecord.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.clinicalrecord.usecase.DeleteClinicalRecordUseCase;
import com.backintro.application.clinicalrecord.usecase.GetClinicalRecordByIdUseCase;
import com.backintro.application.clinicalrecord.usecase.ListClinicalRecordUseCase;
import com.backintro.application.clinicalrecord.usecase.RegisterClinicalRecordUseCase;
import com.backintro.application.clinicalrecord.usecase.UpdateClinicalRecordUseCase;
import com.backintro.domain.clinicalrecord.port.repository.ClinicalRecordRepository;
import com.backintro.domain.clinicalrecordstatus.port.repository.ClinicalRecordStatusRepository;
import com.backintro.domain.patient.port.repository.PatientRepository;

@Configuration
public class ClinicalRecordBeanConfiguration {

    @Bean
    RegisterClinicalRecordUseCase registerClinicalRecordUseCase(
            ClinicalRecordRepository clinicalRecordRepository,
            PatientRepository patientRepository,
            ClinicalRecordStatusRepository statusRepository
    ) {
        return new RegisterClinicalRecordUseCase(
                clinicalRecordRepository,
                patientRepository,
                statusRepository
        );
    }

    @Bean
    GetClinicalRecordByIdUseCase getClinicalRecordByIdUseCase(
            ClinicalRecordRepository repository
    ) {
        return new GetClinicalRecordByIdUseCase(repository);
    }

    @Bean
    ListClinicalRecordUseCase listClinicalRecordUseCase(
            ClinicalRecordRepository repository
    ) {
        return new ListClinicalRecordUseCase(repository);
    }

    @Bean
    UpdateClinicalRecordUseCase updateClinicalRecordUseCase(
            ClinicalRecordRepository clinicalRecordRepository,
            ClinicalRecordStatusRepository statusRepository
    ) {
        return new UpdateClinicalRecordUseCase(
                clinicalRecordRepository,
                statusRepository
        );
    }

    @Bean
    DeleteClinicalRecordUseCase deleteClinicalRecordUseCase(
            ClinicalRecordRepository repository
    ) {
        return new DeleteClinicalRecordUseCase(repository);
    }
}
