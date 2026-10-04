package com.backintro.application.professionaltype.usecase;

import com.backintro.application.professionaltype.command.RegisterProfessionalTypeCommand;
import com.backintro.application.professionaltype.dto.ProfessionalTypeResponse;
import com.backintro.domain.professionaltype.model.aggregate.ProfessionalType;
import com.backintro.domain.professionaltype.port.repository.ProfessionalTypeRepository;

public class RegisterProfessionalTypeUseCase {

    private final ProfessionalTypeRepository professionalTypeRepository;

    public RegisterProfessionalTypeUseCase(
            ProfessionalTypeRepository professionalTypeRepository
    ) {
        this.professionalTypeRepository = professionalTypeRepository;
    }

    public ProfessionalTypeResponse execute(
            RegisterProfessionalTypeCommand command
    ) {
        ProfessionalType professionalType =
                ProfessionalType.register(command.name());

        ProfessionalType saved =
                professionalTypeRepository.save(professionalType);

        return new ProfessionalTypeResponse(
                saved.id().value(),
                saved.name()
        );
    }
}
