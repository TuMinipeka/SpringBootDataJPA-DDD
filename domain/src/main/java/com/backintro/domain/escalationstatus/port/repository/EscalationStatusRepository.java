package com.backintro.domain.escalationstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.escalationstatus.model.aggregate.EscalationStatus;
import com.backintro.domain.escalationstatus.model.valueobject.EscalationStatusId;

public interface EscalationStatusRepository {

    EscalationStatus save(EscalationStatus escalationStatus);

    Optional<EscalationStatus> findById(EscalationStatusId id);

    List<EscalationStatus> findAll();

    boolean existsByNameStatus(String nameStatus);

    void delete(EscalationStatus escalationStatus);
}
