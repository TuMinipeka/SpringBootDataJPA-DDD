package com.backintro.domain.clinicalrecordstatus.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.clinicalrecordstatus.model.aggregate.ClinicalRecordStatus;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;

public interface ClinicalRecordStatusRepository {

    ClinicalRecordStatus save(ClinicalRecordStatus clinicalRecordStatus);

    Optional<ClinicalRecordStatus> findById(ClinicalRecordStatusId id);

    List<ClinicalRecordStatus> findAll();

    boolean existsByCode(String code);

    boolean existsByName(String name);

    void delete(ClinicalRecordStatus clinicalRecordStatus);
}
