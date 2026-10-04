package com.backintro.domain.riskassessment.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.riskassessment.model.aggregate.RiskAssessment;
import com.backintro.domain.riskassessment.model.valueobject.RiskAssessmentId;

public interface RiskAssessmentRepository {

    RiskAssessment save(RiskAssessment riskAssessment);

    Optional<RiskAssessment> findById(RiskAssessmentId id);

    List<RiskAssessment> findAll();

    void delete(RiskAssessment riskAssessment);
}
