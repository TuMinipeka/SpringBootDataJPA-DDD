package com.backintro.application.chatairunmetric.usecase;

import com.backintro.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import com.backintro.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import com.backintro.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.backintro.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class GetChatAiRunMetricByIdUseCase {

    private final ChatAiRunMetricRepository metricRepository;

    public GetChatAiRunMetricByIdUseCase(
            ChatAiRunMetricRepository metricRepository
    ) {
        this.metricRepository = metricRepository;
    }

    public ChatAiRunMetricResponse execute(ChatAiRunMetricId id) {
        var metric = metricRepository.findById(id)
                .orElseThrow(() ->
                        new ChatAiRunMetricNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(metric);
    }

    private ChatAiRunMetricResponse toResponse(ChatAiRunMetric metric) {
        return new ChatAiRunMetricResponse(
                metric.id().value(),
                metric.aiRunId().value(),
                metric.promptTokens(),
                metric.completionTokens(),
                metric.totalTokens(),
                metric.cost()
        );
    }
}
