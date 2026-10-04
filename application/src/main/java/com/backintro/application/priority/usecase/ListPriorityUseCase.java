package com.backintro.application.priority.usecase;

import java.util.List;

import com.backintro.application.priority.dto.PriorityResponse;
import com.backintro.domain.priority.port.repository.PriorityRepository;

public class ListPriorityUseCase {

    private final PriorityRepository priorityRepository;

    public ListPriorityUseCase(
            PriorityRepository priorityRepository
    ) {
        this.priorityRepository = priorityRepository;
    }

    public List<PriorityResponse> execute() {
        return priorityRepository.findAll()
                .stream()
                .map(priority ->
                        new PriorityResponse(
                                priority.id().value(),
                                priority.namePriority()
                        )
                )
                .toList();
    }
}
