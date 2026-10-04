package com.backintro.application.diagnosticsystem.dto;

import java.util.UUID;

public record DiagnosticSystemResponse(
        UUID id,
        String name,
        String code,
        String version
) {
}
