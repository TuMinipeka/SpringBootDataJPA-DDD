package com.backintro.application.risklevel.usecase;

import com.backintro.application.risklevel.command.UpdateRiskLevelCommand;
import com.backintro.application.risklevel.dto.RiskLevelResponse;
import com.backintro.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;

public class UpdateRiskLevelUseCase {

    private final RiskLevelRepository riskLevelRepository;

    public UpdateRiskLevelUseCase(RiskLevelRepository riskLevelRepository) {
        this.riskLevelRepository = riskLevelRepository;
    }

    public RiskLevelResponse execute(UpdateRiskLevelCommand command) {
        var riskLevel = riskLevelRepository
                .findById(command.id())
                .orElseThrow(() ->
                        new RiskLevelNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );

        riskLevel.update(
                command.name(),
                command.code(),
                command.severity()
        );

        var updated = riskLevelRepository.save(riskLevel);

        return new RiskLevelResponse(
                updated.id().value(),
                updated.name(),
                updated.code(),
                updated.severity()
        );
    }
}
