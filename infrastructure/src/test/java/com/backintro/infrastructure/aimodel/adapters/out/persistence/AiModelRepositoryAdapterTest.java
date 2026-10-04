package com.backintro.infrastructure.aimodel.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.aimodel.model.aggregate.AiModel;
import com.backintro.domain.aiprovidermodel.model.valueobject.AiProviderModelId;
import com.backintro.infrastructure.aimodel.adapters.out.persistence.entity.AiModelJpaEntity;
import com.backintro.infrastructure.aimodel.adapters.out.persistence.mapper.AiModelPersistenceMapper;
import com.backintro.infrastructure.aimodel.adapters.out.persistence.repository.AiModelJpaRepository;

@ExtendWith(MockitoExtension.class)
class AiModelRepositoryAdapterTest {

    @Mock
    private AiModelJpaRepository springDataRepository;

    private AiModelRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new AiModelRepositoryAdapter(
                springDataRepository,
                new AiModelPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        AiModel aiModel = newAiModel();
        when(springDataRepository.findById(aiModel.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(AiModelJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AiModel saved = adapter.save(aiModel);

        assertThat(saved.id()).isEqualTo(aiModel.id());
        assertThat(saved.providerModelId())
                .isEqualTo(aiModel.providerModelId());
        assertThat(saved.nameModel()).isEqualTo("GPT-4.1");
        assertThat(saved.modelKey()).isEqualTo("gpt-4.1");
        assertThat(saved.inputTokenPrice())
                .isEqualByComparingTo("0.00000200");
        assertThat(saved.outputTokenPrice())
                .isEqualByComparingTo("0.00000800");
        assertThat(saved.maxTokens()).isEqualTo(32768);
        assertThat(saved.contextWindow()).isEqualTo(1048576);
        assertThat(saved.active()).isTrue();
        verify(springDataRepository).save(any(AiModelJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        AiModel aiModel = newAiModel();

        adapter.delete(aiModel);

        verify(springDataRepository)
                .deleteById(aiModel.id().value());
    }

    private AiModel newAiModel() {
        return AiModel.register(
                AiProviderModelId.generate(),
                "GPT-4.1",
                "gpt-4.1",
                new BigDecimal("0.00000200"),
                new BigDecimal("0.00000800"),
                32768,
                1048576
        );
    }
}
