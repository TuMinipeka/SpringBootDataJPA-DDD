package com.backintro.application.aimodel.usecase;

import java.time.LocalDateTime;

import com.backintro.application.aimodel.exception.AiModelNotFoundApplicationException;
import com.backintro.domain.aimodel.event.AiModelDeletedEvent;
import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.aimodel.port.repository.AiModelRepository;

public class DeleteAiModelUseCase {

    private final AiModelRepository aiModelRepository;

    public DeleteAiModelUseCase(
            AiModelRepository aiModelRepository
    ) {
        this.aiModelRepository = aiModelRepository;
    }

    public AiModelDeletedEvent execute(AiModelId id) {
        var aiModel = aiModelRepository.findById(id)
                .orElseThrow(() ->
                        new AiModelNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        aiModelRepository.delete(aiModel);

        return new AiModelDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
