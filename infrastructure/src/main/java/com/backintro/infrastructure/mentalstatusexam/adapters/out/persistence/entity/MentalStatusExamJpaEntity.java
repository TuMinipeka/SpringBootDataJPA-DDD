package com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "mental_status_exams",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_mental_status_exams_encounter",
                columnNames = {"encounter_id"}
        )
)
public class MentalStatusExamJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "encounter_id", nullable = false, updatable = false)
    private UUID encounterId;

    @Column(name = "appearance", columnDefinition = "TEXT")
    private String appearance;

    @Column(name = "behavior", columnDefinition = "TEXT")
    private String behavior;

    @Column(name = "attitude", columnDefinition = "TEXT")
    private String attitude;

    @Column(name = "consciousness", columnDefinition = "TEXT")
    private String consciousness;

    @Column(name = "orientation", columnDefinition = "TEXT")
    private String orientation;

    @Column(name = "attention", columnDefinition = "TEXT")
    private String attention;

    @Column(name = "memory", columnDefinition = "TEXT")
    private String memory;

    @Column(name = "speech", columnDefinition = "TEXT")
    private String speech;

    @Column(name = "mood", columnDefinition = "TEXT")
    private String mood;

    @Column(name = "affect", columnDefinition = "TEXT")
    private String affect;

    @Column(name = "thought_process", columnDefinition = "TEXT")
    private String thoughtProcess;

    @Column(name = "thought_content", columnDefinition = "TEXT")
    private String thoughtContent;

    @Column(name = "perception", columnDefinition = "TEXT")
    private String perception;

    @Column(name = "judgment", columnDefinition = "TEXT")
    private String judgment;

    @Column(name = "insight", columnDefinition = "TEXT")
    private String insight;

    @Column(name = "psychomotor_activity", columnDefinition = "TEXT")
    private String psychomotorActivity;

    @Column(name = "observations", columnDefinition = "TEXT")
    private String observations;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by", updatable = false)
    private UUID createdBy;

    protected MentalStatusExamJpaEntity() {
        // Required by JPA.
    }

    public MentalStatusExamJpaEntity(
            UUID id,
            UUID encounterId,
            String appearance,
            String behavior,
            String attitude,
            String consciousness,
            String orientation,
            String attention,
            String memory,
            String speech,
            String mood,
            String affect,
            String thoughtProcess,
            String thoughtContent,
            String perception,
            String judgment,
            String insight,
            String psychomotorActivity,
            String observations
    ) {
        this.id = id;
        this.encounterId = encounterId;
        this.appearance = appearance;
        this.behavior = behavior;
        this.attitude = attitude;
        this.consciousness = consciousness;
        this.orientation = orientation;
        this.attention = attention;
        this.memory = memory;
        this.speech = speech;
        this.mood = mood;
        this.affect = affect;
        this.thoughtProcess = thoughtProcess;
        this.thoughtContent = thoughtContent;
        this.perception = perception;
        this.judgment = judgment;
        this.insight = insight;
        this.psychomotorActivity = psychomotorActivity;
        this.observations = observations;
    }

    public void synchronize(
            String appearance,
            String behavior,
            String attitude,
            String consciousness,
            String orientation,
            String attention,
            String memory,
            String speech,
            String mood,
            String affect,
            String thoughtProcess,
            String thoughtContent,
            String perception,
            String judgment,
            String insight,
            String psychomotorActivity,
            String observations
    ) {
        this.appearance = appearance;
        this.behavior = behavior;
        this.attitude = attitude;
        this.consciousness = consciousness;
        this.orientation = orientation;
        this.attention = attention;
        this.memory = memory;
        this.speech = speech;
        this.mood = mood;
        this.affect = affect;
        this.thoughtProcess = thoughtProcess;
        this.thoughtContent = thoughtContent;
        this.perception = perception;
        this.judgment = judgment;
        this.insight = insight;
        this.psychomotorActivity = psychomotorActivity;
        this.observations = observations;
    }

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getEncounterId() {
        return encounterId;
    }

    public String getAppearance() {
        return appearance;
    }

    public String getBehavior() {
        return behavior;
    }

    public String getAttitude() {
        return attitude;
    }

    public String getConsciousness() {
        return consciousness;
    }

    public String getOrientation() {
        return orientation;
    }

    public String getAttention() {
        return attention;
    }

    public String getMemory() {
        return memory;
    }

    public String getSpeech() {
        return speech;
    }

    public String getMood() {
        return mood;
    }

    public String getAffect() {
        return affect;
    }

    public String getThoughtProcess() {
        return thoughtProcess;
    }

    public String getThoughtContent() {
        return thoughtContent;
    }

    public String getPerception() {
        return perception;
    }

    public String getJudgment() {
        return judgment;
    }

    public String getInsight() {
        return insight;
    }

    public String getPsychomotorActivity() {
        return psychomotorActivity;
    }

    public String getObservations() {
        return observations;
    }
}
