package com.backintro.domain.chatairunmetric.model.aggregate;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.chatairun.model.valueobject.ChatAiRunId;
import com.backintro.domain.chatairunmetric.event.ChatAiRunMetricRegisteredEvent;
import com.backintro.domain.chatairunmetric.event.ChatAiRunMetricUpdatedEvent;
import com.backintro.domain.chatairunmetric.model.valueobject.ChatAiRunMetricId;
import com.backintro.domain.common.model.AggregateRoot;

public class ChatAiRunMetric extends AggregateRoot {

    private final ChatAiRunMetricId id;
    private final ChatAiRunId aiRunId;
    private int promptTokens;
    private int completionTokens;
    private int totalTokens;
    private BigDecimal cost;

    private ChatAiRunMetric(
            ChatAiRunMetricId id,
            ChatAiRunId aiRunId,
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.aiRunId = Objects.requireNonNull(
                aiRunId,
                "aiRunId must not be null"
        );
        this.promptTokens = requireNonNegative(
                promptTokens,
                "promptTokens"
        );
        this.completionTokens = requireNonNegative(
                completionTokens,
                "completionTokens"
        );
        this.totalTokens = requireNonNegative(totalTokens, "totalTokens");
        this.cost = requireNonNegative(cost, "cost");
    }

    public static ChatAiRunMetric register(
            ChatAiRunId aiRunId,
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost
    ) {
        ChatAiRunMetricId id = ChatAiRunMetricId.generate();
        ChatAiRunMetric metric = new ChatAiRunMetric(
                id,
                aiRunId,
                promptTokens,
                completionTokens,
                totalTokens,
                cost
        );

        metric.recordEvent(
                new ChatAiRunMetricRegisteredEvent(id, LocalDateTime.now())
        );

        return metric;
    }

    public static ChatAiRunMetric restore(
            ChatAiRunMetricId id,
            ChatAiRunId aiRunId,
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost
    ) {
        return new ChatAiRunMetric(
                id,
                aiRunId,
                promptTokens,
                completionTokens,
                totalTokens,
                cost
        );
    }

    public void update(
            int promptTokens,
            int completionTokens,
            int totalTokens,
            BigDecimal cost
    ) {
        this.promptTokens = requireNonNegative(
                promptTokens,
                "promptTokens"
        );
        this.completionTokens = requireNonNegative(
                completionTokens,
                "completionTokens"
        );
        this.totalTokens = requireNonNegative(totalTokens, "totalTokens");
        this.cost = requireNonNegative(cost, "cost");

        recordEvent(
                new ChatAiRunMetricUpdatedEvent(
                        this.id,
                        this.promptTokens,
                        this.completionTokens,
                        this.totalTokens,
                        this.cost,
                        LocalDateTime.now()
                )
        );
    }

    private static int requireNonNegative(int value, String fieldName) {
        if (value < 0) {
            throw new IllegalArgumentException(
                    fieldName + " must be greater than or equal to zero"
            );
        }
        return value;
    }

    private static BigDecimal requireNonNegative(
            BigDecimal value,
            String fieldName
    ) {
        Objects.requireNonNull(value, fieldName + " must not be null");
        if (value.signum() < 0) {
            throw new IllegalArgumentException(
                    fieldName + " must be greater than or equal to zero"
            );
        }
        return value;
    }

    public ChatAiRunMetricId id() {
        return id;
    }

    public ChatAiRunId aiRunId() {
        return aiRunId;
    }

    public int promptTokens() {
        return promptTokens;
    }

    public int completionTokens() {
        return completionTokens;
    }

    public int totalTokens() {
        return totalTokens;
    }

    public BigDecimal cost() {
        return cost;
    }
}
