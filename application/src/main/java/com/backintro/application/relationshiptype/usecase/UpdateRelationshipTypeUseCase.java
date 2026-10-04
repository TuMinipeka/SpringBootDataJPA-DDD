package com.backintro.application.relationshiptype.usecase;

import com.backintro.application.relationshiptype.command.UpdateRelationshipTypeCommand;
import com.backintro.application.relationshiptype.dto.RelationshipTypeResponse;
import com.backintro.application.relationshiptype.exception.RelationshipTypeNotFoundApplicationException;
import com.backintro.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class UpdateRelationshipTypeUseCase {

    private final RelationshipTypeRepository relationshipTypeRepository;

    public UpdateRelationshipTypeUseCase(
            RelationshipTypeRepository relationshipTypeRepository
    ) {
        this.relationshipTypeRepository = relationshipTypeRepository;
    }

    public RelationshipTypeResponse execute(
            UpdateRelationshipTypeCommand command
    ) {
        var relationshipType = relationshipTypeRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new RelationshipTypeNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        relationshipType.update(command.description());

        var updated = relationshipTypeRepository.save(relationshipType);

        return new RelationshipTypeResponse(
                updated.id().value(),
                updated.description()
        );
    }
}
