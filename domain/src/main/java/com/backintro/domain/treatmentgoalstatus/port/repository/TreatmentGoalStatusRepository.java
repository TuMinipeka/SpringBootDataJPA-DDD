package com.backintro.domain.treatmentgoalstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.treatmentgoalstatus.model.aggregate.TreatmentGoalStatus;
import com.backintro.domain.treatmentgoalstatus.model.valueobject.TreatmentGoalStatusId;

public interface TreatmentGoalStatusRepository {

    TreatmentGoalStatus save(TreatmentGoalStatus treatmentGoalStatus);

    Optional<TreatmentGoalStatus> findById(TreatmentGoalStatusId id);

    List<TreatmentGoalStatus> findAll();

    boolean existsByCode(String code);

    boolean existsByName(String name);

    void delete(TreatmentGoalStatus treatmentGoalStatus);
}
