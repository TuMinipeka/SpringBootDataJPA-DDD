package com.backintro.infrastructure.relationshiptype.adapters.out.persistence.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.backintro.infrastructure.relationshiptype.adapters.out.persistence.entity.RelationshipTypeJpaEntity;

public interface RelationshipTypeJpaRepository
        extends JpaRepository<RelationshipTypeJpaEntity, UUID> {

    boolean existsByDescriptionIgnoreCase(String description);
}
