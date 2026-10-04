package com.backintro.application.relationshiptype.dto;

import java.util.UUID;

public record RelationshipTypeResponse(
        UUID id,
        String description
) {
}
