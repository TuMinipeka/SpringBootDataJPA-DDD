package com.backintro.infrastructure.patientallergy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.patientallergy.usecase.DeletePatientAllergyUseCase;
import com.backintro.application.patientallergy.usecase.GetPatientAllergyByIdUseCase;
import com.backintro.application.patientallergy.usecase.ListPatientAllergyUseCase;
import com.backintro.application.patientallergy.usecase.RegisterPatientAllergyUseCase;
import com.backintro.application.patientallergy.usecase.UpdatePatientAllergyUseCase;
import com.backintro.domain.patient.port.repository.PatientRepository;
import com.backintro.domain.patientallergy.port.repository.PatientAllergyRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

@Configuration
public class PatientAllergyBeanConfiguration {

    @Bean
    RegisterPatientAllergyUseCase registerPatientAllergyUseCase(
            PatientAllergyRepository allergyRepository,
            PatientRepository patientRepository,
            ProfessionalRepository professionalRepository
    ) {
        return new RegisterPatientAllergyUseCase(
                allergyRepository,
                patientRepository,
                professionalRepository
        );
    }

    @Bean
    GetPatientAllergyByIdUseCase getPatientAllergyByIdUseCase(
            PatientAllergyRepository repository
    ) {
        return new GetPatientAllergyByIdUseCase(repository);
    }

    @Bean
    ListPatientAllergyUseCase listPatientAllergyUseCase(
            PatientAllergyRepository repository
    ) {
        return new ListPatientAllergyUseCase(repository);
    }

    @Bean
    UpdatePatientAllergyUseCase updatePatientAllergyUseCase(
            PatientAllergyRepository repository
    ) {
        return new UpdatePatientAllergyUseCase(repository);
    }

    @Bean
    DeletePatientAllergyUseCase deletePatientAllergyUseCase(
            PatientAllergyRepository repository
    ) {
        return new DeletePatientAllergyUseCase(repository);
    }
}
