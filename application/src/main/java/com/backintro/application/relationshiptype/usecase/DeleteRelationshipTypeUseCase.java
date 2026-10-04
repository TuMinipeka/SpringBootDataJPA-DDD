package com.backintro.application.relationshiptype.usecase;

import java.time.LocalDateTime;

import com.backintro.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.backintro.domain.relationshiptype.event.RelationshipTypeDeletedEvent;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.backintro.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class DeleteRelationshipTypeUseCase {

    private final RelationshipTypeRepository relationshipTypeRepository;

    public DeleteRelationshipTypeUseCase(
            RelationshipTypeRepository relationshipTypeRepository
    ) {
        this.relationshipTypeRepository = relationshipTypeRepository;
    }

    public RelationshipTypeDeletedEvent execute(RelationshipTypeId id) {
        var relationshipType = relationshipTypeRepository.findById(id)
                .orElseThrow(() ->
                        new RelationshipTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        relationshipTypeRepository.delete(relationshipType);

        return new RelationshipTypeDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
