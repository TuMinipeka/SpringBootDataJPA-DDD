package com.backintro.application.gender.usecase;

import com.backintro.application.gender.command.RegisterGenderCommand;
import com.backintro.application.gender.dto.GenderResponse;
import com.backintro.domain.gender.model.aggregate.Gender;
import com.backintro.domain.gender.port.repository.GenderRepository;

public class RegisterGenderUseCase {

    private final GenderRepository genderRepository;

    public RegisterGenderUseCase(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public GenderResponse execute(RegisterGenderCommand command) {
        Gender gender = Gender.register(command.description());
        Gender saved = genderRepository.save(gender);

        return new GenderResponse(
                saved.id().value(),
                saved.description()
        );
    }
}
