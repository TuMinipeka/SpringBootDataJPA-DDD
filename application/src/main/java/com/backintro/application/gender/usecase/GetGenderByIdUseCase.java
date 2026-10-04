package com.backintro.application.gender.usecase;

import com.backintro.application.gender.dto.GenderResponse;
import com.backintro.application.gender.exception.GenderNotFoundApplicationException;
import com.backintro.domain.gender.model.valueobject.GenderId;
import com.backintro.domain.gender.port.repository.GenderRepository;

public class GetGenderByIdUseCase {

    private final GenderRepository genderRepository;

    public GetGenderByIdUseCase(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public GenderResponse execute(GenderId id) {
        var gender = genderRepository.findById(id)
                .orElseThrow(() ->
                        new GenderNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new GenderResponse(
                gender.id().value(),
                gender.description()
        );
    }
}
