package com.backintro.infrastructure.riskassessment.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "risk_assessments")
public class RiskAssessmentJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "encounter_id", nullable = false, updatable = false)
    private UUID encounterId;

    @Column(name = "risk_level_id", nullable = false)
    private UUID riskLevelId;

    @Column(name = "suicidal_ideation", nullable = false)
    private boolean suicidalIdeation;

    @Column(name = "suicide_plan", nullable = false)
    private boolean suicidePlan;

    @Column(name = "suicide_intent", nullable = false)
    private boolean suicideIntent;

    @Column(name = "self_harm", nullable = false)
    private boolean selfHarm;

    @Column(name = "harm_to_others", nullable = false)
    private boolean harmToOthers;

    @Column(name = "risk_factors", columnDefinition = "TEXT")
    private String riskFactors;

    @Column(name = "protective_factors", columnDefinition = "TEXT")
    private String protectiveFactors;

    @Column(name = "clinical_actions", columnDefinition = "TEXT")
    private String clinicalActions;

    @Column(name = "observations", columnDefinition = "TEXT")
    private String observations;

    @Column(name = "assessed_at", nullable = false, updatable = false)
    private LocalDateTime assessedAt;

    @Column(name = "assessed_by", nullable = false, updatable = false)
    private UUID assessedBy;

    protected RiskAssessmentJpaEntity() {
        // Required by JPA.
    }

    public RiskAssessmentJpaEntity(
            UUID id,
            UUID encounterId,
            UUID riskLevelId,
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
            UUID assessedBy
    ) {
        this.id = id;
        this.encounterId = encounterId;
        this.riskLevelId = riskLevelId;
        this.suicidalIdeation = suicidalIdeation;
        this.suicidePlan = suicidePlan;
        this.suicideIntent = suicideIntent;
        this.selfHarm = selfHarm;
        this.harmToOthers = harmToOthers;
        this.riskFactors = riskFactors;
        this.protectiveFactors = protectiveFactors;
        this.clinicalActions = clinicalActions;
        this.observations = observations;
        this.assessedAt = assessedAt;
        this.assessedBy = assessedBy;
    }

    public void synchronize(
            UUID riskLevelId,
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
        this.riskLevelId = riskLevelId;
        this.suicidalIdeation = suicidalIdeation;
        this.suicidePlan = suicidePlan;
        this.suicideIntent = suicideIntent;
        this.selfHarm = selfHarm;
        this.harmToOthers = harmToOthers;
        this.riskFactors = riskFactors;
        this.protectiveFactors = protectiveFactors;
        this.clinicalActions = clinicalActions;
        this.observations = observations;
    }

    @PrePersist
    void onCreate() {
        if (assessedAt == null) {
            assessedAt = LocalDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getEncounterId() {
        return encounterId;
    }

    public UUID getRiskLevelId() {
        return riskLevelId;
    }

    public boolean isSuicidalIdeation() {
        return suicidalIdeation;
    }

    public boolean isSuicidePlan() {
        return suicidePlan;
    }

    public boolean isSuicideIntent() {
        return suicideIntent;
    }

    public boolean isSelfHarm() {
        return selfHarm;
    }

    public boolean isHarmToOthers() {
        return harmToOthers;
    }

    public String getRiskFactors() {
        return riskFactors;
    }

    public String getProtectiveFactors() {
        return protectiveFactors;
    }

    public String getClinicalActions() {
        return clinicalActions;
    }

    public String getObservations() {
        return observations;
    }

    public LocalDateTime getAssessedAt() {
        return assessedAt;
    }

    public UUID getAssessedBy() {
        return assessedBy;
    }
}
