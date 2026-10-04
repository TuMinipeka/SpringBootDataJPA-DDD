package com.backintro.application.risklevel.usecase;

import com.backintro.application.risklevel.dto.RiskLevelResponse;
import com.backintro.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;

public class GetRiskLevelByIdUseCase {

    private final RiskLevelRepository riskLevelRepository;

    public GetRiskLevelByIdUseCase(RiskLevelRepository riskLevelRepository) {
        this.riskLevelRepository = riskLevelRepository;
    }

    public RiskLevelResponse execute(RiskLevelId id) {
        var riskLevel = riskLevelRepository.findById(id)
                .orElseThrow(() ->
                        new RiskLevelNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return new RiskLevelResponse(
                riskLevel.id().value(),
                riskLevel.name(),
                riskLevel.code(),
                riskLevel.severity()
        );
    }
}
