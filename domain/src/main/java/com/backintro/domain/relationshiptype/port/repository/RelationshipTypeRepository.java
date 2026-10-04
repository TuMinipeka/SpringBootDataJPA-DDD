package com.backintro.domain.relationshiptype.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.relationshiptype.model.aggregate.RelationshipType;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public interface RelationshipTypeRepository {

    RelationshipType save(RelationshipType relationshipType);

    Optional<RelationshipType> findById(RelationshipTypeId id);

    List<RelationshipType> findAll();

    boolean existsByDescription(String description);

    void delete(RelationshipType relationshipType);
}
