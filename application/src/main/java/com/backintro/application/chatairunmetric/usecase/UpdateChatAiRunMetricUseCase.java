package com.backintro.application.chatairunmetric.usecase;

import com.backintro.application.chatairunmetric.command.UpdateChatAiRunMetricCommand;
import com.backintro.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import com.backintro.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import com.backintro.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.backintro.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class UpdateChatAiRunMetricUseCase {

    private final ChatAiRunMetricRepository metricRepository;

    public UpdateChatAiRunMetricUseCase(
            ChatAiRunMetricRepository metricRepository
    ) {
        this.metricRepository = metricRepository;
    }

    public ChatAiRunMetricResponse execute(
            UpdateChatAiRunMetricCommand command
    ) {
        var metric = metricRepository.findById(command.id())
                .orElseThrow(() ->
                        new ChatAiRunMetricNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        metric.update(
                command.promptTokens(),
                command.completionTokens(),
                command.totalTokens(),
                command.cost()
        );

        return toResponse(metricRepository.save(metric));
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
