package com.backintro.application.airunstatus.usecase;

import java.time.LocalDateTime;

import com.backintro.application.airunstatus.exception.AiRunStatusNotFoundApplicationException;
import com.backintro.domain.airunstatus.event.AiRunStatusDeletedEvent;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;
import com.backintro.domain.airunstatus.port.repository.AiRunStatusRepository;

public class DeleteAiRunStatusUseCase {

    private final AiRunStatusRepository aiRunStatusRepository;

    public DeleteAiRunStatusUseCase(
            AiRunStatusRepository aiRunStatusRepository
    ) {
        this.aiRunStatusRepository = aiRunStatusRepository;
    }

    public AiRunStatusDeletedEvent execute(AiRunStatusId id) {
        var aiRunStatus = aiRunStatusRepository.findById(id)
                .orElseThrow(() ->
                        new AiRunStatusNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        aiRunStatusRepository.delete(aiRunStatus);

        return new AiRunStatusDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
