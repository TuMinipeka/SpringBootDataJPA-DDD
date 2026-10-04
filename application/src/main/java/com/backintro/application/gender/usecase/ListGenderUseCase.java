package com.backintro.application.gender.usecase;

import java.util.List;

import com.backintro.application.gender.dto.GenderResponse;
import com.backintro.domain.gender.port.repository.GenderRepository;

public class ListGenderUseCase {

    private final GenderRepository genderRepository;

    public ListGenderUseCase(GenderRepository genderRepository) {
        this.genderRepository = genderRepository;
    }

    public List<GenderResponse> execute() {
        return genderRepository.findAll()
                .stream()
                .map(gender ->
                        new GenderResponse(
                                gender.id().value(),
                                gender.description()
                        )
                )
                .toList();
    }
}
