package com.backintro.application.aiprovidermodel.usecase;

import com.backintro.application.aiprovidermodel.command.RegisterAiProviderModelCommand;
import com.backintro.application.aiprovidermodel.dto.AiProviderModelResponse;
import com.backintro.domain.aiprovidermodel.model.aggregate.AiProviderModel;
import com.backintro.domain.aiprovidermodel.port.repository.AiProviderModelRepository;

public class RegisterAiProviderModelUseCase {

    private final AiProviderModelRepository aiProviderModelRepository;

    public RegisterAiProviderModelUseCase(
            AiProviderModelRepository aiProviderModelRepository
    ) {
        this.aiProviderModelRepository = aiProviderModelRepository;
    }

    public AiProviderModelResponse execute(
            RegisterAiProviderModelCommand command
    ) {
        AiProviderModel aiProviderModel =
                AiProviderModel.register(
                        command.nameProviderAi(),
                        command.razonSocial(),
                        command.sitioWeb()
                );
        AiProviderModel saved =
                aiProviderModelRepository.save(aiProviderModel);

        return new AiProviderModelResponse(
                saved.id().value(),
                saved.nameProviderAi(),
                saved.razonSocial(),
                saved.sitioWeb()
        );
    }
}
