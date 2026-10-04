package com.backintro.domain.aimodel.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aimodel.model.valueobject.AiModelId;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;

public interface AiModelRepository {

    AiModel save(AiModel aiModel);

    Optional<AiModel> findById(AiModelId id);

    List<AiModel> findAll();

    boolean existsByProviderModelIdAndModelKey(
            AiProviderModelId providerModelId,
            String modelKey
    );

    void delete(AiModel aiModel);
}
