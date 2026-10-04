package com.backintro.application.priority.usecase;

import java.time.LocalDateTime;

import com.backintro.application.priority.exception.PriorityNotFoundApplicationException;
import com.backintro.domain.priority.event.PriorityDeletedEvent;
import com.backintro.domain.priority.model.valueobject.PriorityId;
import com.backintro.domain.priority.port.repository.PriorityRepository;

public class DeletePriorityUseCase {

    private final PriorityRepository priorityRepository;

    public DeletePriorityUseCase(
            PriorityRepository priorityRepository
    ) {
        this.priorityRepository = priorityRepository;
    }

    public PriorityDeletedEvent execute(PriorityId id) {
        var priority = priorityRepository.findById(id)
                .orElseThrow(() ->
                        new PriorityNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        priorityRepository.delete(priority);

        return new PriorityDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
