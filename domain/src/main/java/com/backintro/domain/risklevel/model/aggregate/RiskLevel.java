package com.backintro.domain.risklevel.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.risklevel.event.RiskLevelRegisteredEvent;
import com.backintro.domain.risklevel.event.RiskLevelUpdatedEvent;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public class RiskLevel extends AggregateRoot {

    private final RiskLevelId id;
    private String name;
    private String code;
    private boolean active;
    private int severity;

    private RiskLevel(
            RiskLevelId id,
            String name,
            String code,
            boolean active,
            int severity
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.active = active;
        this.severity = requireNonNegativeSeverity(severity);
    }

    public static RiskLevel register(
            String name,
            String code,
            int severity
    ) {
        RiskLevelId id = RiskLevelId.generate();
        RiskLevel riskLevel = new RiskLevel(
                id,
                name,
                code,
                true,
                severity
        );

        riskLevel.recordEvent(
                new RiskLevelRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return riskLevel;
    }

    public static RiskLevel restore(
            RiskLevelId id,
            String name,
            String code,
            boolean active,
            int severity
    ) {
        return new RiskLevel(id, name, code, active, severity);
    }

    public void update(
            String name,
            String code,
            int severity
    ) {
        this.name = Objects.requireNonNull(name, "name must not be null");
        this.code = Objects.requireNonNull(code, "code must not be null");
        this.severity = requireNonNegativeSeverity(severity);

        recordEvent(
                new RiskLevelUpdatedEvent(
                        this.id,
                        this.name,
                        this.code,
                        this.severity,
                        LocalDateTime.now()
                )
        );
    }

    private static int requireNonNegativeSeverity(int severity) {
        if (severity < 0) {
            throw new IllegalArgumentException(
                    "severity must be greater than or equal to zero"
            );
        }
        return severity;
    }

    public RiskLevelId id() {
        return id;
    }

    public String name() {
        return name;
    }

    public String code() {
        return code;
    }

    public boolean active() {
        return active;
    }

    public int severity() {
        return severity;
    }
}
