package com.backintro.application.professional.usecase;

import java.util.List;

import com.backintro.application.professional.dto.ProfessionalResponse;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class ListProfessionalUseCase {

    private final ProfessionalRepository professionalRepository;

    public ListProfessionalUseCase(
            ProfessionalRepository professionalRepository
    ) {
        this.professionalRepository = professionalRepository;
    }

    public List<ProfessionalResponse> execute() {
        return professionalRepository.findAll()
                .stream()
                .map(professional ->
                        new ProfessionalResponse(
                                professional.id().value(),
                                professional.documentNumber(),
                                professional.firstName(),
                                professional.lastName(),
                                professional.licenseNumber()
                        )
                )
                .toList();
    }
}
