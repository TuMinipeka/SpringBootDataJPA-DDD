package com.backintro.application.professionaltype.usecase;

import java.util.List;

import com.backintro.application.professionaltype.dto.ProfessionalTypeResponse;
import com.backintro.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class ListProfessionalTypeUseCase {

    private final ProfessionalTypeRepository professionalTypeRepository;

    public ListProfessionalTypeUseCase(
            ProfessionalTypeRepository professionalTypeRepository
    ) {
        this.professionalTypeRepository = professionalTypeRepository;
    }

    public List<ProfessionalTypeResponse> execute() {
        return professionalTypeRepository.findAll()
                .stream()
                .map(professionalType ->
                        new ProfessionalTypeResponse(
                                professionalType.id().value(),
                                professionalType.name()
                        )
                )
                .toList();
    }
}
