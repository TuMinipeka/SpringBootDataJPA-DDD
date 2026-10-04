package com.backintro.application.aiprovidermodel.usecase;

import com.backintro.application.aiprovidermodel.dto.AiProviderModelResponse;
import com.backintro.application.aiprovidermodel.exception.AiProviderModelNotFoundApplicationException;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;
import com.backintro.domain.aiprovidermodel.port.repository.AiProviderModelRepository;

public class GetAiProviderModelByIdUseCase {

    private final AiProviderModelRepository aiProviderModelRepository;

    public GetAiProviderModelByIdUseCase(
            AiProviderModelRepository aiProviderModelRepository
    ) {
        this.aiProviderModelRepository = aiProviderModelRepository;
    }

    public AiProviderModelResponse execute(AiProviderModelId id) {
        var aiProviderModel = aiProviderModelRepository.findById(id)
                .orElseThrow(() ->
                        new AiProviderModelNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new AiProviderModelResponse(
                aiProviderModel.id().value(),
                aiProviderModel.nameProviderAi(),
                aiProviderModel.razonSocial(),
                aiProviderModel.sitioWeb()
        );
    }
}
