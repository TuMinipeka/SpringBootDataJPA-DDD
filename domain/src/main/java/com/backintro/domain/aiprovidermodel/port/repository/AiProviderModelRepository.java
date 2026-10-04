package com.backintro.domain.aiprovidermodel.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.aiprovidermodel.model.aggregate.AiProviderModel;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;

public interface AiProviderModelRepository {

    AiProviderModel save(AiProviderModel aiProviderModel);

    Optional<AiProviderModel> findById(AiProviderModelId id);

    List<AiProviderModel> findAll();

    boolean existsByNameProviderAi(String nameProviderAi);

    void delete(AiProviderModel aiProviderModel);
}
