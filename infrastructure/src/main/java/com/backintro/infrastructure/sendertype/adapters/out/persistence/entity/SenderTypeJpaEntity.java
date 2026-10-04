package com.backintro.infrastructure.sendertype.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "sender_types")
public class SenderTypeJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name_type", nullable = false, unique = true, length = 50)
    private String nameType;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected SenderTypeJpaEntity() {
        // Required by JPA.
    }

    public SenderTypeJpaEntity(UUID id, String nameType) {
        this.id = id;
        this.nameType = nameType;
    }

    public void synchronize(String nameType) {
        this.nameType = nameType;
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

    public String getNameType() {
        return nameType;
    }
}
