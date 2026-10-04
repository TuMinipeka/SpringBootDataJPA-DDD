package com.backintro.application.chatairunmetric.usecase;

import com.backintro.application.chatairun.exception.ChatAiRunNotFoundApplicationException;
import com.backintro.application.chatairunmetric.command.RegisterChatAiRunMetricCommand;
import com.backintro.application.chatairunmetric.dto.ChatAiRunMetricResponse;
import com.backintro.domain.chatairun.port.repository.ChatAiRunRepository;
import com.backintro.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.backintro.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class RegisterChatAiRunMetricUseCase {

    private final ChatAiRunMetricRepository metricRepository;
    private final ChatAiRunRepository chatAiRunRepository;

    public RegisterChatAiRunMetricUseCase(
            ChatAiRunMetricRepository metricRepository,
            ChatAiRunRepository chatAiRunRepository
    ) {
        this.metricRepository = metricRepository;
        this.chatAiRunRepository = chatAiRunRepository;
    }

    public ChatAiRunMetricResponse execute(
            RegisterChatAiRunMetricCommand command
    ) {
        chatAiRunRepository.findById(command.aiRunId())
                .orElseThrow(() ->
                        new ChatAiRunNotFoundApplicationException(
                                command.aiRunId().value().toString()
                        )
                );

        ChatAiRunMetric metric = ChatAiRunMetric.register(
                command.aiRunId(),
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
