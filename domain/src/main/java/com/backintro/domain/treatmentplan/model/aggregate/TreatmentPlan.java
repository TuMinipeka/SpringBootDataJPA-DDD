package com.backintro.domain.treatmentplan.model.aggregate;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.treatmentplan.event.TreatmentPlanRegisteredEvent;
import com.backintro.domain.treatmentplan.event.TreatmentPlanUpdatedEvent;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;
import com.backintro.domain.treatmentstatus.model.valueobject.TreatmentStatusId;

public class TreatmentPlan extends AggregateRoot {

    private final TreatmentPlanId id;
    private final EncounterId encounterId;
    private final ProfessionalId professionalId;
    private final LocalDate startDate;
    private String title;
    private String description;
    private LocalDate endDate;
    private TreatmentStatusId statusId;

    private TreatmentPlan(
            TreatmentPlanId id,
            EncounterId encounterId,
            ProfessionalId professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            TreatmentStatusId statusId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.encounterId = Objects.requireNonNull(
                encounterId,
                "encounterId must not be null"
        );
        this.professionalId = Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.description = description;
        this.startDate = Objects.requireNonNull(
                startDate,
                "startDate must not be null"
        );
        this.endDate = endDate;
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );
    }

    public static TreatmentPlan register(
            EncounterId encounterId,
            ProfessionalId professionalId,
            String title,
            String description,
            LocalDate startDate,
            TreatmentStatusId statusId
    ) {
        TreatmentPlanId id = TreatmentPlanId.generate();
        TreatmentPlan treatmentPlan = new TreatmentPlan(
                id,
                encounterId,
                professionalId,
                title,
                description,
                startDate,
                null,
                statusId
        );

        treatmentPlan.recordEvent(
                new TreatmentPlanRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return treatmentPlan;
    }

    public static TreatmentPlan restore(
            TreatmentPlanId id,
            EncounterId encounterId,
            ProfessionalId professionalId,
            String title,
            String description,
            LocalDate startDate,
            LocalDate endDate,
            TreatmentStatusId statusId
    ) {
        return new TreatmentPlan(
                id,
                encounterId,
                professionalId,
                title,
                description,
                startDate,
                endDate,
                statusId
        );
    }

    public void update(
            String title,
            String description,
            LocalDate endDate,
            TreatmentStatusId statusId
    ) {
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.description = description;
        this.endDate = endDate;
        this.statusId = Objects.requireNonNull(
                statusId,
                "statusId must not be null"
        );

        recordEvent(
                new TreatmentPlanUpdatedEvent(
                        this.id,
                        this.title,
                        this.description,
                        this.endDate,
                        this.statusId,
                        LocalDateTime.now()
                )
        );
    }

    public TreatmentPlanId id() {
        return id;
    }

    public EncounterId encounterId() {
        return encounterId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public String title() {
        return title;
    }

    public String description() {
        return description;
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    public TreatmentStatusId statusId() {
        return statusId;
    }
}
