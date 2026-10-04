package com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.aiprovidermodel.model.aggregate.AiProviderModel;
import com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.entity.AiProviderModelJpaEntity;
import com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.mapper.AiProviderModelPersistenceMapper;
import com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.repository.AiProviderModelJpaRepository;

@ExtendWith(MockitoExtension.class)
class AiProviderModelRepositoryAdapterTest {

    @Mock
    private AiProviderModelJpaRepository springDataRepository;

    private AiProviderModelRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new AiProviderModelRepositoryAdapter(
                springDataRepository,
                new AiProviderModelPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        AiProviderModel aiProviderModel =
                newAiProviderModel();
        when(springDataRepository.findById(aiProviderModel.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(AiProviderModelJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AiProviderModel saved = adapter.save(aiProviderModel);

        assertThat(saved.id()).isEqualTo(aiProviderModel.id());
        assertThat(saved.nameProviderAi()).isEqualTo("OpenAI");
        assertThat(saved.razonSocial()).isEqualTo("OpenAI, L.L.C.");
        assertThat(saved.sitioWeb()).isEqualTo("https://openai.com");
        assertThat(saved.active()).isTrue();
        verify(springDataRepository).save(any(AiProviderModelJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        AiProviderModel aiProviderModel =
                newAiProviderModel();

        adapter.delete(aiProviderModel);

        verify(springDataRepository)
                .deleteById(aiProviderModel.id().value());
    }

    private AiProviderModel newAiProviderModel() {
        return AiProviderModel.register(
                "OpenAI",
                "OpenAI, L.L.C.",
                "https://openai.com"
        );
    }
}
