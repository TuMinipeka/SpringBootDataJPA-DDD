package com.backintro.application.aiprovidermodel.usecase;

import java.util.List;

import com.backintro.application.aiprovidermodel.dto.AiProviderModelResponse;
import com.backintro.domain.aiprovidermodel.port.repository.AiProviderModelRepository;

public class ListAiProviderModelUseCase {

    private final AiProviderModelRepository aiProviderModelRepository;

    public ListAiProviderModelUseCase(
            AiProviderModelRepository aiProviderModelRepository
    ) {
        this.aiProviderModelRepository = aiProviderModelRepository;
    }

    public List<AiProviderModelResponse> execute() {
        return aiProviderModelRepository.findAll()
                .stream()
                .map(aiProviderModel ->
                        new AiProviderModelResponse(
                                aiProviderModel.id().value(),
                                aiProviderModel.nameProviderAi(),
                                aiProviderModel.razonSocial(),
                                aiProviderModel.sitioWeb()
                        )
                )
                .toList();
    }
}
