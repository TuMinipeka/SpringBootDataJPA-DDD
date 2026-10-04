package com.backintro.application.chatairunmetric.usecase;

import java.util.List;

import com.backintro.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import com.backintro.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.backintro.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class ListChatAiRunMetricUseCase {

    private final ChatAiRunMetricRepository metricRepository;

    public ListChatAiRunMetricUseCase(
            ChatAiRunMetricRepository metricRepository
    ) {
        this.metricRepository = metricRepository;
    }

    public List<ChatAiRunMetricResponse> execute() {
        return metricRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
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
