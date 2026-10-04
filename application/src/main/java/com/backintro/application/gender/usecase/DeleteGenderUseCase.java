package com.backintro.application.gender.usecase;

import java.time.LocalDateTime;

import com.backintro.application.gender.exception.GenderNotFoundApplicationException;
import com.backintro.domain.gender.event.GenderDeletedEvent;
import com.backintro.domain.gender.model.valueobject.GenderId;
import com.backintro.domain.gender.port.repository.GenderRepository;

public class DeleteGenderUseCase {

    private final GenderRepository genderRepository;

    public DeleteGenderUseCase(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public GenderDeletedEvent execute(GenderId id) {
        var gender = genderRepository.findById(id)
                .orElseThrow(() ->
                        new GenderNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        genderRepository.delete(gender);

        return new GenderDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
