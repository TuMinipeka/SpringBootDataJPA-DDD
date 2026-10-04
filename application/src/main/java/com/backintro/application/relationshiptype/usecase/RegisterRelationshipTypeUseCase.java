package com.backintro.application.relationshiptype.usecase;

import com.backintro.application.relationshiptype.command.RegisterRelationshipTypeCommand;
import com.backintro.application.relationshiptype.dto.RelationshipTypeResponse;
import com.backintro.domain.relationshiptype.model.aggregate.RelationshipType;
import com.backintro.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class RegisterRelationshipTypeUseCase {

    private final RelationshipTypeRepository relationshipTypeRepository;

    public RegisterRelationshipTypeUseCase(
            RelationshipTypeRepository relationshipTypeRepository
    ) {
        this.relationshipTypeRepository = relationshipTypeRepository;
    }

    public RelationshipTypeResponse execute(
            RegisterRelationshipTypeCommand command
    ) {
        RelationshipType relationshipType =
                RelationshipType.register(command.description());
        RelationshipType saved =
                relationshipTypeRepository.save(relationshipType);

        return new RelationshipTypeResponse(
                saved.id().value(),
                saved.description()
        );
    }
}
