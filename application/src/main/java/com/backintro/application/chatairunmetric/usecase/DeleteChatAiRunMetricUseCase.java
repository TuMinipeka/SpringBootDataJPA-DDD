package com.backintro.application.chatairunmetric.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatairunmetric.exception.ChatAiRunMetricNotFoundApplicationException;
import com.backintro.domain.chatairunmetric.event.ChatAiRunMetricDeletedEvent;
import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.backintro.domain.chatairunmetric.port.repository.ChatAiRunMetricRepository;

public class DeleteChatAiRunMetricUseCase {

    private final ChatAiRunMetricRepository metricRepository;

    public DeleteChatAiRunMetricUseCase(
            ChatAiRunMetricRepository metricRepository
    ) {
        this.metricRepository = metricRepository;
    }

    public ChatAiRunMetricDeletedEvent execute(ChatAiRunMetricId id) {
        var metric = metricRepository.findById(id)
                .orElseThrow(() ->
                        new ChatAiRunMetricNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        metricRepository.delete(metric);

        return new ChatAiRunMetricDeletedEvent(id, LocalDateTime.now());
    }
}
