package com.backintro.infrastructure.professionalstudy.adapters.out.persistence.entity;

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
        name = "professional_studies",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_professional_studies",
                columnNames = {"professional_id", "study_id", "title"}
        )
)
public class ProfessionalStudyJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "study_id", nullable = false, updatable = false)
    private UUID studyId;

    @Column(name = "professional_id", nullable = false, updatable = false)
    private UUID professionalId;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "university", length = 100)
    private String university;

    @Column(name = "is_valid", nullable = false)
    private boolean valid;

    @Column(name = "resolution_number", length = 60)
    private String resolutionNumber;

    @Column(name = "country_id", updatable = false)
    private UUID countryId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected ProfessionalStudyJpaEntity() {
        // Required by JPA.
    }

    public ProfessionalStudyJpaEntity(
            UUID id,
            UUID studyId,
            UUID professionalId,
            String title,
            String university,
            boolean valid,
            String resolutionNumber,
            UUID countryId
    ) {
        this.id = id;
        this.studyId = studyId;
        this.professionalId = professionalId;
        this.title = title;
        this.university = university;
        this.valid = valid;
        this.resolutionNumber = resolutionNumber;
        this.countryId = countryId;
    }

    public void synchronize(
            String title,
            String university,
            boolean valid,
            String resolutionNumber
    ) {
        this.title = title;
        this.university = university;
        this.valid = valid;
        this.resolutionNumber = resolutionNumber;
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

    public UUID getStudyId() {
        return studyId;
    }

    public UUID getProfessionalId() {
        return professionalId;
    }

    public String getTitle() {
        return title;
    }

    public String getUniversity() {
        return university;
    }

    public boolean isValid() {
        return valid;
    }

    public String getResolutionNumber() {
        return resolutionNumber;
    }

    public UUID getCountryId() {
        return countryId;
    }
}
