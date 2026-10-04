package com.backintro.domain.treatmentgoal.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.treatmentgoal.event.TreatmentGoalRegisteredEvent;
import com.backintro.domain.treatmentgoal.event.TreatmentGoalUpdatedEvent;
import com.backintro.domain.treatmentgoal.model.valueobject.TreatmentGoalId;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public class TreatmentGoal extends AggregateRoot {

    private final TreatmentGoalId id;
    private final TreatmentPlanId treatmentPlanId;
    private String description;
    private LocalDate targetDate;
    private LocalDateTime completedAt;
    private String notes;
    private TreatmentGoalStatusId statusId;

    private TreatmentGoal(
            TreatmentGoalId id,
            TreatmentPlanId treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            TreatmentGoalStatusId statusId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.treatmentPlanId = Objects.requireNonNull(
                treatmentPlanId,
                "treatmentPlanId must not be null"
        );
        this.description = Objects.requireNonNull(
                description,
                "description must not be null"
        );
        this.targetDate = targetDate;
        this.completedAt = completedAt;
        this.notes = notes;
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );
    }

    public static TreatmentGoal register(
            TreatmentPlanId treatmentPlanId,
            String description,
            LocalDate targetDate,
            String notes,
            TreatmentGoalStatusId statusId
    ) {
        TreatmentGoalId id = TreatmentGoalId.generate();
        TreatmentGoal treatmentGoal = new TreatmentGoal(
                id,
                treatmentPlanId,
                description,
                targetDate,
                null,
                notes,
                statusId
        );

        treatmentGoal.recordEvent(
                new TreatmentGoalRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return treatmentGoal;
    }

    public static TreatmentGoal restore(
            TreatmentGoalId id,
            TreatmentPlanId treatmentPlanId,
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            TreatmentGoalStatusId statusId
    ) {
        return new TreatmentGoal(
                id,
                treatmentPlanId,
                description,
                targetDate,
                completedAt,
                notes,
                statusId
        );
    }

    public void update(
            String description,
            LocalDate targetDate,
            LocalDateTime completedAt,
            String notes,
            TreatmentGoalStatusId statusId
    ) {
        this.description = Objects.requireNonNull(
                description,
                "description must not be null"
        );
        this.targetDate = targetDate;
        this.completedAt = completedAt;
        this.notes = notes;
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );

        recordEvent(
                new TreatmentGoalUpdatedEvent(
                        this.id,
                        this.description,
                        this.targetDate,
                        this.completedAt,
                        this.notes,
                        this.statusId,
                        LocalDateTime.now()
                )
        );
    }

    public TreatmentGoalId id() {
        return id;
    }

    public TreatmentPlanId treatmentPlanId() {
        return treatmentPlanId;
    }

    public String description() {
        return description;
    }

    public LocalDate targetDate() {
        return targetDate;
    }

    public LocalDateTime completedAt() {
        return completedAt;
    }

    public String notes() {
        return notes;
    }

    public TreatmentGoalStatusId statusId() {
        return statusId;
    }
}
