package com.backintro.domain.riskassessment.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.riskassessment.event.RiskAssessmentRegisteredEvent;
import com.backintro.domain.riskassessment.event.RiskAssessmentUpdatedEvent;
import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public class RiskAssessment extends AggregateRoot {

    private final RiskAssessmentId id;
    private final EncounterId encounterId;
    private final LocalDateTime assessedAt;
    private final ProfessionalId assessedBy;
    private RiskLevelId riskLevelId;
    private boolean suicidalIdeation;
    private boolean suicidePlan;
    private boolean suicideIntent;
    private boolean selfHarm;
    private boolean harmToOthers;
    private String riskFactors;
    private String protectiveFactors;
    private String clinicalActions;
    private String observations;

    private RiskAssessment(
            RiskAssessmentId id,
            EncounterId encounterId,
            RiskLevelId riskLevelId,
            boolean suicidalIdeation,
            boolean suicidePlan,
            boolean suicideIntent,
            boolean selfHarm,
            boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            ProfessionalId assessedBy
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.encounterId = Objects.requireNonNull(
                encounterId,
                "encounterId must not be null"
        );
        this.riskLevelId = Objects.requireNonNull(
                riskLevelId,
                "riskLevelId must not be null"
        );
        this.suicidalIdeation = suicidalIdeation;
        this.suicidePlan = suicidePlan;
        this.suicideIntent = suicideIntent;
        this.selfHarm = selfHarm;
        this.harmToOthers = harmToOthers;
        this.riskFactors = riskFactors;
        this.protectiveFactors = protectiveFactors;
        this.clinicalActions = clinicalActions;
        this.observations = observations;
        this.assessedAt = Objects.requireNonNull(
                assessedAt,
                "assessedAt must not be null"
        );
        this.assessedBy = Objects.requireNonNull(
                assessedBy,
                "assessedBy must not be null"
        );
    }

    public static RiskAssessment register(
            EncounterId encounterId,
            RiskLevelId riskLevelId,
            boolean suicidalIdeation,
            boolean suicidePlan,
            boolean suicideIntent,
            boolean selfHarm,
            boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            ProfessionalId assessedBy
    ) {
        RiskAssessmentId id = RiskAssessmentId.generate();
        LocalDateTime now = LocalDateTime.now();
        RiskAssessment riskAssessment = new RiskAssessment(
                id,
                encounterId,
                riskLevelId,
                suicidalIdeation,
                suicidePlan,
                suicideIntent,
                selfHarm,
                harmToOthers,
                riskFactors,
                protectiveFactors,
                clinicalActions,
                observations,
                now,
                assessedBy
        );

        riskAssessment.recordEvent(
                new RiskAssessmentRegisteredEvent(id, now)
        );

        return riskAssessment;
    }

    public static RiskAssessment restore(
            RiskAssessmentId id,
            EncounterId encounterId,
            RiskLevelId riskLevelId,
            boolean suicidalIdeation,
            boolean suicidePlan,
            boolean suicideIntent,
            boolean selfHarm,
            boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations,
            LocalDateTime assessedAt,
            ProfessionalId assessedBy
    ) {
        return new RiskAssessment(
                id,
                encounterId,
                riskLevelId,
                suicidalIdeation,
                suicidePlan,
                suicideIntent,
                selfHarm,
                harmToOthers,
                riskFactors,
                protectiveFactors,
                clinicalActions,
                observations,
                assessedAt,
                assessedBy
        );
    }

    public void update(
            RiskLevelId riskLevelId,
            boolean suicidalIdeation,
            boolean suicidePlan,
            boolean suicideIntent,
            boolean selfHarm,
            boolean harmToOthers,
            String riskFactors,
            String protectiveFactors,
            String clinicalActions,
            String observations
    ) {
        this.riskLevelId = Objects.requireNonNull(
                riskLevelId,
                "riskLevelId must not be null"
        );
        this.suicidalIdeation = suicidalIdeation;
        this.suicidePlan = suicidePlan;
        this.suicideIntent = suicideIntent;
        this.selfHarm = selfHarm;
        this.harmToOthers = harmToOthers;
        this.riskFactors = riskFactors;
        this.protectiveFactors = protectiveFactors;
        this.clinicalActions = clinicalActions;
        this.observations = observations;

        recordEvent(
                new RiskAssessmentUpdatedEvent(
                        this.id,
                        this.riskLevelId,
                        this.suicidalIdeation,
                        this.suicidePlan,
                        this.suicideIntent,
                        this.selfHarm,
                        this.harmToOthers,
                        this.riskFactors,
                        this.protectiveFactors,
                        this.clinicalActions,
                        this.observations,
                        LocalDateTime.now()
                )
        );
    }

    public RiskAssessmentId id() {
        return id;
    }

    public EncounterId encounterId() {
        return encounterId;
    }

    public RiskLevelId riskLevelId() {
        return riskLevelId;
    }

    public boolean suicidalIdeation() {
        return suicidalIdeation;
    }

    public boolean suicidePlan() {
        return suicidePlan;
    }

    public boolean suicideIntent() {
        return suicideIntent;
    }

    public boolean selfHarm() {
        return selfHarm;
    }

    public boolean harmToOthers() {
        return harmToOthers;
    }

    public String riskFactors() {
        return riskFactors;
    }

    public String protectiveFactors() {
        return protectiveFactors;
    }

    public String clinicalActions() {
        return clinicalActions;
    }

    public String observations() {
        return observations;
    }

    public LocalDateTime assessedAt() {
        return assessedAt;
    }

    public ProfessionalId assessedBy() {
        return assessedBy;
    }
}
