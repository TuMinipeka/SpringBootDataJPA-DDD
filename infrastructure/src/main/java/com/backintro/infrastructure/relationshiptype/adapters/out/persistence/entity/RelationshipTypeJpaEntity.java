package com.backintro.infrastructure.relationshiptype.adapters.out.persistence.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "relationship_types")
public class RelationshipTypeJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "description", nullable = false, unique = true, length = 50)
    private String description;

    protected RelationshipTypeJpaEntity() {
        // Required by JPA.
    }

    public RelationshipTypeJpaEntity(UUID id, String description) {
        this.id = id;
        this.description = description;
    }

    public void synchronize(String description) {
        this.description = description;
    }

    public UUID getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
