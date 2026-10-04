package com.backintro.application.risklevel.usecase;

import java.util.List;

import com.backintro.application.risklevel.dto.RiskLevelResponse;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;

public class ListRiskLevelUseCase {

    private final RiskLevelRepository riskLevelRepository;

    public ListRiskLevelUseCase(RiskLevelRepository riskLevelRepository) {
        this.riskLevelRepository = riskLevelRepository;
    }

    public List<RiskLevelResponse> execute() {
        return riskLevelRepository.findAll()
                .stream()
                .map(riskLevel ->
                        new RiskLevelResponse(
                                riskLevel.id().value(),
                                riskLevel.name(),
                                riskLevel.code(),
                                riskLevel.severity()
                        )
                )
                .toList();
    }
}
