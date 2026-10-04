package com.backintro.domain.risklevel.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.risklevel.model.aggregate.RiskLevel;
import com.backintro.domain.risklevel.model.valueobject.RiskLevelId;

public interface RiskLevelRepository {

    RiskLevel save(RiskLevel riskLevel);

    Optional<RiskLevel> findById(RiskLevelId id);

    List<RiskLevel> findAll();

    boolean existsByCode(String code);

    boolean existsByName(String name);

    void delete(RiskLevel riskLevel);
}
