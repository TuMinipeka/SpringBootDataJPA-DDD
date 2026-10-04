package com.backintro.infrastructure.patientcontact.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.patientcontact.usecase.DeletePatientContactUseCase;
import com.backintro.application.patientcontact.usecase.GetPatientContactByIdUseCase;
import com.backintro.application.patientcontact.usecase.ListPatientContactUseCase;
import com.backintro.application.patientcontact.usecase.RegisterPatientContactUseCase;
import com.backintro.application.patientcontact.usecase.UpdatePatientContactUseCase;
import com.backintro.domain.contact.port.repository.ContactRepository;
import com.backintro.domain.patient.port.repository.PatientRepository;
import com.backintro.domain.patientcontact.port.repository.PatientContactRepository;
import com.backintro.domain.relationshiptype.port.repository.RelationshipTypeRepository;

@Configuration
public class PatientContactBeanConfiguration {

    @Bean
    RegisterPatientContactUseCase registerPatientContactUseCase(
            PatientContactRepository patientContactRepository,
            ContactRepository contactRepository,
            PatientRepository patientRepository,
            RelationshipTypeRepository relationshipTypeRepository
    ) {
        return new RegisterPatientContactUseCase(
                patientContactRepository,
                contactRepository,
                patientRepository,
                relationshipTypeRepository
        );
    }

    @Bean
    GetPatientContactByIdUseCase getPatientContactByIdUseCase(
            PatientContactRepository repository
    ) {
        return new GetPatientContactByIdUseCase(repository);
    }

    @Bean
    ListPatientContactUseCase listPatientContactUseCase(
            PatientContactRepository repository
    ) {
        return new ListPatientContactUseCase(repository);
    }

    @Bean
    UpdatePatientContactUseCase updatePatientContactUseCase(
            PatientContactRepository repository
    ) {
        return new UpdatePatientContactUseCase(repository);
    }

    @Bean
    DeletePatientContactUseCase deletePatientContactUseCase(
            PatientContactRepository repository
    ) {
        return new DeletePatientContactUseCase(repository);
    }
}
