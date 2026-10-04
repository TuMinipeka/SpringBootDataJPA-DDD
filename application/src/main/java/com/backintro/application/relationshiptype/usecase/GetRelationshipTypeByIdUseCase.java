package com.backintro.application.relationshiptype.usecase;

import com.backintro.application.relationshiptype.dto.RelationshipTypeResponse;
import com.backintro.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.backintro.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class GetRelationshipTypeByIdUseCase {

    private final RelationshipTypeRepository relationshipTypeRepository;

    public GetRelationshipTypeByIdUseCase(
            RelationshipTypeRepository relationshipTypeRepository
    ) {
        this.relationshipTypeRepository = relationshipTypeRepository;
    }

    public RelationshipTypeResponse execute(RelationshipTypeId id) {
        var relationshipType = relationshipTypeRepository.findById(id)
                .orElseThrow(() ->
                        new RelationshipTypeNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new RelationshipTypeResponse(
                relationshipType.id().value(),
                relationshipType.description()
        );
    }
}
