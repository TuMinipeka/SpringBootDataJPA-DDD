package com.backintro.application.gender.usecase;

import com.backintro.application.gender.command.UpdateGenderCommand;
import com.backintro.application.gender.dto.GenderResponse;
import com.backintro.application.gender.exception.GenderNotFoundApplicationException;
import com.backintro.domain.gender.port.repository.GenderRepository;

public class UpdateGenderUseCase {

    private final GenderRepository genderRepository;

    public UpdateGenderUseCase(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public GenderResponse execute(UpdateGenderCommand command) {
        var gender = genderRepository.findById(command.id())
                .orElseThrow(() ->
                        new GenderNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        gender.update(command.description());

        var updated = genderRepository.save(gender);

        return new GenderResponse(
                updated.id().value(),
                updated.description()
        );
    }
}
