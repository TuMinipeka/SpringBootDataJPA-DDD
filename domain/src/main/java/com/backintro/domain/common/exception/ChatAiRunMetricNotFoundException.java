package com.backintro.domain.common.exception;

import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;

public class ChatAiRunMetricNotFoundException extends RuntimeException {

    public ChatAiRunMetricNotFoundException(ChatAiRunMetricId id) {
        super("Chat AI run metric not found with id: " + id.value());
    }
}
