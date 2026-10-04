package com.backintro.infrastructure.stateregion.adapters.out.persistence.entity;

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
        name = "state_regions",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_state_regions_country_code",
                columnNames = {"country_id", "code_region"}
        )
)
public class StateRegionJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name_region", nullable = false, length = 50)
    private String nameRegion;

    @Column(name = "code_region", nullable = false, length = 10)
    private String codeRegion;

    @Column(name = "description", length = 100)
    private String description;

    @Column(name = "is_active", nullable = false)
    private boolean active;

    @Column(name = "country_id", nullable = false, updatable = false)
    private UUID countryId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected StateRegionJpaEntity() {
        // Required by JPA.
    }

    public StateRegionJpaEntity(
            UUID id,
            UUID countryId,
            String nameRegion,
            String codeRegion,
            boolean active
    ) {
        this.id = id;
        this.countryId = countryId;
        this.nameRegion = nameRegion;
        this.codeRegion = codeRegion;
        this.active = active;
    }

    public void synchronize(
            String nameRegion,
            String codeRegion,
            boolean active
    ) {
        this.nameRegion = nameRegion;
        this.codeRegion = codeRegion;
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

    public UUID getCountryId() {
        return countryId;
    }

    public String getNameRegion() {
        return nameRegion;
    }

    public String getCodeRegion() {
        return codeRegion;
    }

    public boolean isActive() {
        return active;
    }
}
