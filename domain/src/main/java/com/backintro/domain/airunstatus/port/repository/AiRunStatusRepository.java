package com.backintro.domain.airunstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.airunstatus.model.aggregate.AiRunStatus;
import com.backintro.domain.airunstatus.model.valueobject.AiRunStatusId;

public interface AiRunStatusRepository {

    AiRunStatus save(AiRunStatus aiRunStatus);

    Optional<AiRunStatus> findById(AiRunStatusId id);

    List<AiRunStatus> findAll();

    boolean existsByNameStatus(String nameStatus);

    void delete(AiRunStatus aiRunStatus);
}
