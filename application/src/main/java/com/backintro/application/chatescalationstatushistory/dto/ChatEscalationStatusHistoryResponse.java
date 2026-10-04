package com.backintro.application.chatescalationstatushistory.dto;

import java.util.UUID;

public record ChatEscalationStatusHistoryResponse(
        UUID id,
        UUID escalationId,
        UUID escalationStatusId
) {
}
