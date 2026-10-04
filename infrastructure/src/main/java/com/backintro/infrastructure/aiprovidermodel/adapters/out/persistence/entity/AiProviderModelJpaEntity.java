package com.backintro.infrastructure.aiprovidermodel.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "provider_models_ai")
public class AiProviderModelJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name_provider_ai", nullable = false, unique = true, length = 100)
    private String nameProviderAi;

    @Column(name = "razon_social", length = 100)
    private String razonSocial;

    @Column(name = "sitio_web", columnDefinition = "TEXT")
    private String sitioWeb;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected AiProviderModelJpaEntity() {
        // Required by JPA.
    }

    public AiProviderModelJpaEntity(
            UUID id,
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean active
    ) {
        this.id = id;
        this.nameProviderAi = nameProviderAi;
        this.razonSocial = razonSocial;
        this.sitioWeb = sitioWeb;
        this.active = active;
    }

    public void synchronize(
            String nameProviderAi,
            String razonSocial,
            String sitioWeb,
            boolean active
    ) {
        this.nameProviderAi = nameProviderAi;
        this.razonSocial = razonSocial;
        this.sitioWeb = sitioWeb;
        this.active = active;
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

    public String getNameProviderAi() {
        return nameProviderAi;
    }

    public String getRazonSocial() {
        return razonSocial;
    }

    public String getSitioWeb() {
        return sitioWeb;
    }

    public boolean isActive() {
        return active;
    }
}
