package com.backintro.domain.chatairunmetric.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunmetric.model.aggregate.ChatAiRunMetric;
import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public interface ChatAiRunMetricRepository {

    ChatAiRunMetric save(ChatAiRunMetric metric);

    Optional<ChatAiRunMetric> findById(ChatAiRunMetricId id);

    List<ChatAiRunMetric> findAll();

    boolean existsByAiRunId(ChatAiRunId aiRunId);

    void delete(ChatAiRunMetric metric);
}
