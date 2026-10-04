package com.backintro.application.priority.usecase;

import com.backintro.application.priority.command.UpdatePriorityCommand;
import com.backintro.application.priority.dto.PriorityResponse;
import com.backintro.application.priority.exception.PriorityNotFoundApplicationException;
import com.backintro.domain.priority.port.repository.PriorityRepository;

public class UpdatePriorityUseCase {

    private final PriorityRepository priorityRepository;

    public UpdatePriorityUseCase(
            PriorityRepository priorityRepository
    ) {
        this.priorityRepository = priorityRepository;
    }

    public PriorityResponse execute(
            UpdatePriorityCommand command
    ) {
        var priority = priorityRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new PriorityNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        priority.update(command.namePriority());

        var updated = priorityRepository.save(priority);

        return new PriorityResponse(
                updated.id().value(),
                updated.namePriority()
        );
    }
}
