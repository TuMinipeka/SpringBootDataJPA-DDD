package com.backintro.application.stateregion.dto;

import java.util.UUID;

public record StateRegionResponse(
        UUID id,
        String nameRegion,
        String codeRegion
) {
}
