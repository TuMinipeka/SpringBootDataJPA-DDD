package com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.backintro.infrastructure.chatairunmetric.adapters.out.persistence.entity.ChatAiRunMetricJpaEntity;

@Component
public class ChatAiRunMetricPersistenceMapper {

    public ChatAiRunMetric toDomain(ChatAiRunMetricJpaEntity entity) {
        return ChatAiRunMetric.restore(
                new ChatAiRunMetricId(entity.getId()),
                new ChatAiRunId(entity.getAiRunId()),
                entity.getPromptTokens(),
                entity.getCompletionTokens(),
                entity.getTotalTokens(),
                entity.getCost()
        );
    }

    public ChatAiRunMetricJpaEntity toNewEntity(ChatAiRunMetric metric) {
        return new ChatAiRunMetricJpaEntity(
                metric.id().value(),
                metric.aiRunId().value(),
                metric.promptTokens(),
                metric.completionTokens(),
                metric.totalTokens(),
                metric.cost()
        );
    }

    public void synchronize(
            ChatAiRunMetric metric,
            ChatAiRunMetricJpaEntity entity
    ) {
        entity.synchronize(
                metric.promptTokens(),
                metric.completionTokens(),
                metric.totalTokens(),
                metric.cost()
        );
    }
}
