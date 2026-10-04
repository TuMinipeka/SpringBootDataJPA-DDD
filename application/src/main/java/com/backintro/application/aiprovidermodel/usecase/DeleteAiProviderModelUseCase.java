package com.backintro.application.aiprovidermodel.usecase;

import java.time.LocalDateTime;

import com.backintro.application.aiprovidermodel.exception.AiProviderModelNotFoundApplicationException;
import com.backintro.domain.aiprovidermodel.event.AiProviderModelDeletedEvent;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;
import com.backintro.domain.aiprovidermodel.port.repository.AiProviderModelRepository;

public class DeleteAiProviderModelUseCase {

    private final AiProviderModelRepository aiProviderModelRepository;

    public DeleteAiProviderModelUseCase(
            AiProviderModelRepository aiProviderModelRepository
    ) {
        this.aiProviderModelRepository = aiProviderModelRepository;
    }

    public AiProviderModelDeletedEvent execute(AiProviderModelId id) {
        var aiProviderModel = aiProviderModelRepository.findById(id)
                .orElseThrow(() ->
                        new AiProviderModelNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        aiProviderModelRepository.delete(aiProviderModel);

        return new AiProviderModelDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
