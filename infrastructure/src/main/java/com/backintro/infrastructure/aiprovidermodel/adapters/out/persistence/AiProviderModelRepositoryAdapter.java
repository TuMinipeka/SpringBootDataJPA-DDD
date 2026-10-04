package com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.aiprovidermodel.model.aggregate.AiProviderModel;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;
import com.backintro.domain.aiprovidermodel.port.repository.AiProviderModelRepository;
import com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.entity.AiProviderModelJpaEntity;
import com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.mapper.AiProviderModelPersistenceMapper;
import com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.repository.AiProviderModelJpaRepository;

@Repository
@Transactional
public class AiProviderModelRepositoryAdapter
        implements AiProviderModelRepository {

    private final AiProviderModelJpaRepository repository;
    private final AiProviderModelPersistenceMapper mapper;

    public AiProviderModelRepositoryAdapter(
            AiProviderModelJpaRepository repository,
            AiProviderModelPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public AiProviderModel save(AiProviderModel aiProviderModel) {
        AiProviderModelJpaEntity entity = repository
                .findById(aiProviderModel.id().value())
                .map(existing -> {
                    mapper.synchronize(aiProviderModel, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(aiProviderModel));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<AiProviderModel> findById(AiProviderModelId id) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<AiProviderModel> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByNameProviderAi(String nameProviderAi) {
        return repository.existsByNameProviderAiIgnoreCase(nameProviderAi);
    }

    @Override
    public void delete(AiProviderModel aiProviderModel) {
        repository.deleteById(aiProviderModel.id().value());
    }
}
