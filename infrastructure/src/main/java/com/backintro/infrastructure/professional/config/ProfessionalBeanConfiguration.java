package com.backintro.infrastructure.professional.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.backintro.application.professional.usecase.DeleteProfessionalUseCase;
import com.backintro.application.professional.usecase.GetProfessionalByIdUseCase;
import com.backintro.application.professional.usecase.ListProfessionalUseCase;
import com.backintro.application.professional.usecase.RegisterProfessionalUseCase;
import com.backintro.application.professional.usecase.UpdateProfessionalUseCase;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.backintro.domain.contact.port.repository.ContactRepository;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.professionaltype.port.repository.ProfessionalTypeRepository;

@Configuration
public class ProfessionalBeanConfiguration {

    @Bean
    RegisterProfessionalUseCase registerProfessionalUseCase(
            ProfessionalRepository professionalRepository,
            DocumentTypeRepository documentTypeRepository,
            ProfessionalTypeRepository professionalTypeRepository,
            CityMunicipalityRepository cityMunicipalityRepository,
            ContactRepository contactRepository
    ) {
        return new RegisterProfessionalUseCase(
                professionalRepository,
                documentTypeRepository,
                professionalTypeRepository,
                cityMunicipalityRepository,
                contactRepository
        );
    }

    @Bean
    GetProfessionalByIdUseCase getProfessionalByIdUseCase(
            ProfessionalRepository repository
    ) {
        return new GetProfessionalByIdUseCase(repository);
    }

    @Bean
    ListProfessionalUseCase listProfessionalUseCase(
            ProfessionalRepository repository
    ) {
        return new ListProfessionalUseCase(repository);
    }

    @Bean
    UpdateProfessionalUseCase updateProfessionalUseCase(
            ProfessionalRepository repository
    ) {
        return new UpdateProfessionalUseCase(repository);
    }

    @Bean
    DeleteProfessionalUseCase deleteProfessionalUseCase(
            ProfessionalRepository repository
    ) {
        return new DeleteProfessionalUseCase(repository);
    }
}
