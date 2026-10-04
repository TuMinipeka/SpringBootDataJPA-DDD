package com.backintro.application.patient.usecase;

import java.util.List;

import com.backintro.application.patient.dto.PatientResponse;
import com.backintro.domain.patient.port.repository.PatientRepository;

public class ListPatientUseCase {

    private final PatientRepository patientRepository;

    public ListPatientUseCase(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public List<PatientResponse> execute() {
        return patientRepository.findAll()
                .stream()
                .map(patient ->
                        new PatientResponse(
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
                        )
                )
                .toList();
    }
}
