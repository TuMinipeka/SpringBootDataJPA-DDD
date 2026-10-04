package com.backintro.infrastructure.airunstatus.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "ai_runs_statuses")
public class AiRunStatusJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name_status", nullable = false, unique = true, length = 50)
    private String nameStatus;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected AiRunStatusJpaEntity() {
        // Required by JPA.
    }

    public AiRunStatusJpaEntity(UUID id, String nameStatus) {
        this.id = id;
        this.nameStatus = nameStatus;
    }

    public void synchronize(String nameStatus) {
        this.nameStatus = nameStatus;
    }

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public String getNameStatus() {
        return nameStatus;
    }
}
