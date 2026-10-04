package com.backintro.application.patientcontact.usecase;

import java.util.List;

import com.backintro.application.patientcontact.dto.PatientContactResponse;
import com.backintro.domain.patientcontact.port.repository.PatientContactRepository;

public class ListPatientContactUseCase {

    private final PatientContactRepository patientContactRepository;

    public ListPatientContactUseCase(
            PatientContactRepository patientContactRepository
    ) {
        this.patientContactRepository = patientContactRepository;
    }

    public List<PatientContactResponse> execute() {
        return patientContactRepository.findAll()
                .stream()
                .map(patientContact ->
                        new PatientContactResponse(
                                patientContact.id().value(),
                                patientContact.primaryContact(),
                                patientContact.emergencyContact()
                        )
                )
                .toList();
    }
}
