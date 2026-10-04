package com.backintro.application.sendertype.dto;

import java.util.UUID;

public record SenderTypeResponse(
        UUID id,
        String nameType
) {
}
