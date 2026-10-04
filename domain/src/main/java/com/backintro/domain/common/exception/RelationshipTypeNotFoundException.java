package com.backintro.domain.common.exception;

import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;

public class RelationshipTypeNotFoundException extends RuntimeException {

    public RelationshipTypeNotFoundException(RelationshipTypeId id) {
        super("Relationship type not found with id: " + id.value());
    }
}
