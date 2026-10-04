package com.backintro.application.aiprovidermodel.usecase;

import com.backintro.application.aiprovidermodel.command.UpdateAiProviderModelCommand;
import com.backintro.application.aiprovidermodel.dto.AiProviderModelResponse;
import com.backintro.application.aiprovidermodel.exception.AiProviderModelNotFoundApplicationException;
import com.backintro.domain.aiprovidermodel.port.repository.AiProviderModelRepository;

public class UpdateAiProviderModelUseCase {

    private final AiProviderModelRepository aiProviderModelRepository;

    public UpdateAiProviderModelUseCase(
            AiProviderModelRepository aiProviderModelRepository
    ) {
        this.aiProviderModelRepository = aiProviderModelRepository;
    }

    public AiProviderModelResponse execute(
            UpdateAiProviderModelCommand command
    ) {
        var aiProviderModel = aiProviderModelRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new AiProviderModelNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        aiProviderModel.update(
                command.nameProviderAi(),
                command.razonSocial(),
                command.sitioWeb()
        );

        var updated = aiProviderModelRepository.save(aiProviderModel);

        return new AiProviderModelResponse(
                updated.id().value(),
                updated.nameProviderAi(),
                updated.razonSocial(),
                updated.sitioWeb()
        );
    }
}
