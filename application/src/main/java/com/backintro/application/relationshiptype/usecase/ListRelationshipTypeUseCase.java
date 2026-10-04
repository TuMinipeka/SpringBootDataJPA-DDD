package com.backintro.application.relationshiptype.usecase;

import java.util.List;

import com.backintro.application.relationshiptype.dto.RelationshipTypeResponse;
import com.backintro.domain.relationshiptype.port.repository.RelationshipTypeRepository;

public class ListRelationshipTypeUseCase {

    private final RelationshipTypeRepository relationshipTypeRepository;

    public ListRelationshipTypeUseCase(
            RelationshipTypeRepository relationshipTypeRepository
    ) {
        this.relationshipTypeRepository = relationshipTypeRepository;
    }

    public List<RelationshipTypeResponse> execute() {
        return relationshipTypeRepository.findAll()
                .stream()
                .map(relationshipType ->
                        new RelationshipTypeResponse(
                                relationshipType.id().value(),
                                relationshipType.description()
                        )
                )
                .toList();
    }
}
