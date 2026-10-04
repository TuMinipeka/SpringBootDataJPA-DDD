package com.backintro.infrastructure.patient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.patient.usecase.DeletePatientUseCase;
import com.backintro.application.patient.usecase.GetPatientByIdUseCase;
import com.backintro.application.patient.usecase.ListPatientUseCase;
import com.backintro.application.patient.usecase.RegisterPatientUseCase;
import com.backintro.application.patient.usecase.UpdatePatientUseCase;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;
import com.backintro.domain.gender.port.repository.GenderRepository;
import com.backintro.domain.patient.port.repository.PatientRepository;

@Configuration
public class PatientBeanConfiguration {

    @Bean
    RegisterPatientUseCase registerPatientUseCase(
            PatientRepository patientRepository,
            DocumentTypeRepository documentTypeRepository,
            GenderRepository genderRepository,
            CityMunicipalityRepository cityMunicipalityRepository
    ) {
        return new RegisterPatientUseCase(
                patientRepository,
                documentTypeRepository,
                genderRepository,
                cityMunicipalityRepository
        );
    }

    @Bean
    GetPatientByIdUseCase getPatientByIdUseCase(PatientRepository repository) {
        return new GetPatientByIdUseCase(repository);
    }

    @Bean
    ListPatientUseCase listPatientUseCase(PatientRepository repository) {
        return new ListPatientUseCase(repository);
    }

    @Bean
    UpdatePatientUseCase updatePatientUseCase(PatientRepository repository) {
        return new UpdatePatientUseCase(repository);
    }

    @Bean
    DeletePatientUseCase deletePatientUseCase(PatientRepository repository) {
        return new DeletePatientUseCase(repository);
    }
}
