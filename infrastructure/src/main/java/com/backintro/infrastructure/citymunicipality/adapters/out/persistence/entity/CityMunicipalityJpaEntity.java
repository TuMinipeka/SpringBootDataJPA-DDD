package com.backintro.infrastructure.citymunicipality.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(
        name = "city_municipalities",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_city_municipalities_region_code",
                columnNames = {"region_id", "code_city"}
        )
)
public class CityMunicipalityJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name_city", nullable = false, length = 50)
    private String nameCity;

    @Column(name = "code_city", nullable = false, length = 10)
    private String codeCity;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "region_id", nullable = false, updatable = false)
    private UUID regionId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected CityMunicipalityJpaEntity() {
        // Required by JPA.
    }

    public CityMunicipalityJpaEntity(
            UUID id,
            UUID regionId,
            String nameCity,
            String codeCity,
            boolean active
    ) {
        this.id = id;
        this.regionId = regionId;
        this.nameCity = nameCity;
        this.codeCity = codeCity;
        this.active = active;
    }

    public void synchronize(
            String nameCity,
            String codeCity,
            boolean active
    ) {
        this.nameCity = nameCity;
        this.codeCity = codeCity;
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

    public UUID getRegionId() {
        return regionId;
    }

    public String getNameCity() {
        return nameCity;
    }

    public String getCodeCity() {
        return codeCity;
    }

    public boolean isActive() {
        return active;
    }
}
