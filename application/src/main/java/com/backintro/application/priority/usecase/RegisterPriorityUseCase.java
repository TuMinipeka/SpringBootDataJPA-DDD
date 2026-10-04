package com.backintro.application.priority.usecase;

import com.backintro.application.priority.command.RegisterPriorityCommand;
import com.backintro.application.priority.dto.PriorityResponse;
import com.backintro.domain.priority.model.aggregate.Priority;
import com.backintro.domain.priority.port.repository.PriorityRepository;

public class RegisterPriorityUseCase {

    private final PriorityRepository priorityRepository;

    public RegisterPriorityUseCase(
            PriorityRepository priorityRepository
    ) {
        this.priorityRepository = priorityRepository;
    }

    public PriorityResponse execute(
            RegisterPriorityCommand command
    ) {
        Priority priority =
                Priority.register(command.namePriority());
        Priority saved =
                priorityRepository.save(priority);

        return new PriorityResponse(
                saved.id().value(),
                saved.namePriority()
        );
    }
}
