package com.backintro.application.risklevel.usecase;

import java.time.LocalDateTime;

import com.backintro.application.risklevel.exception.RiskLevelNotFoundApplicationException;
import com.backintro.domain.risklevel.event.RiskLevelDeletedEvent;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;
import com.backintro.domain.risklevel.port.repository.RiskLevelRepository;

public class DeleteRiskLevelUseCase {

    private final RiskLevelRepository riskLevelRepository;

    public DeleteRiskLevelUseCase(RiskLevelRepository riskLevelRepository) {
        this.riskLevelRepository = riskLevelRepository;
    }

    public RiskLevelDeletedEvent execute(RiskLevelId id) {
        var riskLevel = riskLevelRepository.findById(id)
                .orElseThrow(() ->
                        new RiskLevelNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        riskLevelRepository.delete(riskLevel);

        return new RiskLevelDeletedEvent(id, LocalDateTime.now());
    }
}
