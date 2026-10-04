package com.backintro.application.professional.usecase;

import com.backintro.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.backintro.application.contact.exception.ContactNotFoundApplicationException;
import com.backintro.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.backintro.application.professional.command.RegisterProfessionalCommand;
import com.backintro.application.professional.dto.ProfessionalResponse;
import com.backintro.application.professionaltype.exception.ProfessionalTypeNotFoundApplicationException;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.backintro.domain.contact.port.repository.ContactRepository;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;
import com.backintro.domain.professional.model.aggregate.Professional;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;
import com.backintro.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class RegisterProfessionalUseCase {

    private final ProfessionalRepository professionalRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final ProfessionalTypeRepository professionalTypeRepository;
    private final CityMunicipalityRepository cityMunicipalityRepository;
    private final ContactRepository contactRepository;

    public RegisterProfessionalUseCase(
            ProfessionalRepository professionalRepository,
            DocumentTypeRepository documentTypeRepository,
            ProfessionalTypeRepository professionalTypeRepository,
            CityMunicipalityRepository cityMunicipalityRepository,
            ContactRepository contactRepository
    ) {
        this.professionalRepository = professionalRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.professionalTypeRepository = professionalTypeRepository;
        this.cityMunicipalityRepository = cityMunicipalityRepository;
        this.contactRepository = contactRepository;
    }

    public ProfessionalResponse execute(RegisterProfessionalCommand command) {
        documentTypeRepository.findById(command.documentTypeId())
                .orElseThrow(() ->
                        new DocumentTypeNotFoundApplicationException(
                                command.documentTypeId().value().toString()
                        )
                );
        professionalTypeRepository.findById(command.professionalTypeId())
                .orElseThrow(() ->
                        new ProfessionalTypeNotFoundApplicationException(
                                command.professionalTypeId().value().toString()
                        )
                );
        if (command.cityId() != null) {
            cityMunicipalityRepository.findById(command.cityId())
                    .orElseThrow(() ->
                            new CityMunicipalityNotFoundApplicationException(
                                    command.cityId().value().toString()
                            )
                    );
        }
        if (command.contactId() != null) {
            contactRepository.findById(command.contactId())
                    .orElseThrow(() ->
                            new ContactNotFoundApplicationException(
                                    command.contactId().value().toString()
                            )
                    );
        }

        Professional professional = Professional.register(
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.lastName(),
                command.professionalTypeId(),
                command.licenseNumber(),
                command.cityId(),
                command.contactId()
        );

        Professional saved = professionalRepository.save(professional);

        return toResponse(saved);
    }

    private ProfessionalResponse toResponse(Professional professional) {
        return new ProfessionalResponse(
                professional.id().value(),
                professional.documentNumber(),
                professional.firstName(),
                professional.lastName(),
                professional.licenseNumber()
        );
    }
}
