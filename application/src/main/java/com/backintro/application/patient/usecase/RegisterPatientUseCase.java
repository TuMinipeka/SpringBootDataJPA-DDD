package com.backintro.application.patient.usecase;

import com.backintro.application.citymunicipality.exception.CityMunicipalityNotFoundApplicationException;
import com.backintro.application.documenttype.exception.DocumentTypeNotFoundApplicationException;
import com.backintro.application.gender.exception.GenderNotFoundApplicationException;
import com.backintro.application.patient.command.RegisterPatientCommand;
import com.backintro.application.patient.dto.PatientResponse;
import com.backintro.domain.citymunicipality.port.repository.CityMunicipalityRepository;
import com.backintro.domain.documenttype.port.repository.DocumentTypeRepository;
import com.backintro.domain.gender.model.valueobject.GenderId;
import com.backintro.domain.gender.port.repository.GenderRepository;
import com.backintro.domain.patient.model.aggregate.Patient;
import com.backintro.domain.patient.port.repository.PatientRepository;

public class RegisterPatientUseCase {

    private final PatientRepository patientRepository;
    private final DocumentTypeRepository documentTypeRepository;
    private final GenderRepository genderRepository;
    private final CityMunicipalityRepository cityMunicipalityRepository;

    public RegisterPatientUseCase(
            PatientRepository patientRepository,
            DocumentTypeRepository documentTypeRepository,
            GenderRepository genderRepository,
            CityMunicipalityRepository cityMunicipalityRepository
    ) {
        this.patientRepository = patientRepository;
        this.documentTypeRepository = documentTypeRepository;
        this.genderRepository = genderRepository;
        this.cityMunicipalityRepository = cityMunicipalityRepository;
    }

    public PatientResponse execute(RegisterPatientCommand command) {
        documentTypeRepository.findById(command.documentTypeId())
                .orElseThrow(() ->
                        new DocumentTypeNotFoundApplicationException(
                                command.documentTypeId().value().toString()
                        )
                );
        validateGender(command.biologicalSexId());
        validateGender(command.genderIdentityId());
        if (command.cityId() != null) {
            cityMunicipalityRepository.findById(command.cityId())
                    .orElseThrow(() ->
                            new CityMunicipalityNotFoundApplicationException(
                                    command.cityId().value().toString()
                            )
                    );
        }

        Patient patient = Patient.register(
                command.documentTypeId(),
                command.documentNumber(),
                command.firstName(),
                command.middleName(),
                command.lastName(),
                command.secondLastName(),
                command.birthDate(),
                command.biologicalSexId(),
                command.genderIdentityId(),
                command.email(),
                command.phone(),
                command.address(),
                command.cityId()
        );

        return toResponse(patientRepository.save(patient));
    }

    private void validateGender(GenderId genderId) {
        if (genderId != null) {
            genderRepository.findById(genderId)
                    .orElseThrow(() ->
                            new GenderNotFoundApplicationException(
                                    genderId.value().toString()
                            )
                    );
        }
    }

    private PatientResponse toResponse(Patient patient) {
        return new PatientResponse(
                patient.id().value(),
                patient.documentNumber(),
                patient.firstName(),
                patient.middleName(),
                patient.lastName(),
                patient.secondLastName(),
                patient.birthDate(),
                patient.email(),
                patient.phone(),
                patient.address()
        );
    }
}
