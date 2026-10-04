package com.backintro.infrastructure.chatairunmetric.adapters.out.persistence;

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

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;
import com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.mapper.ChatAiRunMetricPersistenceMapper;
import com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.repository.ChatAiRunMetricJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatAiRunMetricRepositoryAdapterTest {

    @Mock
    private ChatAiRunMetricJpaRepository springDataRepository;

    private ChatAiRunMetricRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatAiRunMetricRepositoryAdapter(
                springDataRepository,
                new ChatAiRunMetricPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatAiRunMetric metric = newMetric();
        when(springDataRepository.findById(metric.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ChatAiRunMetricJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ChatAiRunMetric saved = adapter.save(metric);

        assertThat(saved.id()).isEqualTo(metric.id());
        assertThat(saved.aiRunId()).isEqualTo(metric.aiRunId());
        assertThat(saved.promptTokens()).isEqualTo(120);
        assertThat(saved.completionTokens()).isEqualTo(80);
        assertThat(saved.totalTokens()).isEqualTo(200);
        assertThat(saved.cost()).isEqualByComparingTo("0.012345");
        verify(springDataRepository)
                .save(any(ChatAiRunMetricJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatAiRunMetric metric = newMetric();

        adapter.delete(metric);

        verify(springDataRepository).deleteById(metric.id().value());
    }

    private ChatAiRunMetric newMetric() {
        return ChatAiRunMetric.register(
                ChatAiRunId.generate(),
                120,
                80,
                200,
                new BigDecimal("0.012345")
        );
    }
}
