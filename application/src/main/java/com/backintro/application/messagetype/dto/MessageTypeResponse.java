package com.backintro.application.messagetype.dto;

import java.util.UUID;

public record MessageTypeResponse(
        UUID id,
        String nameType
) {
}
