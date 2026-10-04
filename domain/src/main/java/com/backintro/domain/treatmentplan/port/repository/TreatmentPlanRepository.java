package com.backintro.domain.treatmentplan.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.treatmentplan.model.aggregate.TreatmentPlan;
import com.backintro.domain.treatmentplan.model.valueobject.TreatmentPlanId;

public interface TreatmentPlanRepository {

    TreatmentPlan save(TreatmentPlan treatmentPlan);

    Optional<TreatmentPlan> findById(TreatmentPlanId id);

    List<TreatmentPlan> findAll();

    void delete(TreatmentPlan treatmentPlan);
}
