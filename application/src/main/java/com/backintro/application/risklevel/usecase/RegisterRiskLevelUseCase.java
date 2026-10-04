package com.backintro.application.risklevel.usecase;

import com.backintro.application.risklevel.command.RegisterRiskLevelCommand;
import com.backintro.application.risklevel.dto.RiskLevelResponse;
import com.backintro.domain.risklevel.model.aggregate.RiskLevel;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;

public class RegisterRiskLevelUseCase {

    private final RiskLevelRepository riskLevelRepository;

    public RegisterRiskLevelUseCase(
            RiskLevelRepository riskLevelRepository
    ) {
        this.riskLevelRepository = riskLevelRepository;
    }

    public RiskLevelResponse execute(RegisterRiskLevelCommand command) {
        RiskLevel riskLevel = RiskLevel.register(
                command.name(),
                command.code(),
                command.severity()
        );

        RiskLevel saved = riskLevelRepository.save(riskLevel);

        return new RiskLevelResponse(
                saved.id().value(),
                saved.name(),
                saved.code(),
                saved.severity()
        );
    }
}
