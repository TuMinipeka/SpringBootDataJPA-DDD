package com.backintro.domain.clinicalrecord.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.backintro.domain.clinicalrecord.model.valueobject.ClinicalRecordId;

public interface ClinicalRecordRepository {

    ClinicalRecord save(ClinicalRecord clinicalRecord);

    Optional<ClinicalRecord> findById(ClinicalRecordId id);

    List<ClinicalRecord> findAll();

    boolean existsByRecordNumber(String recordNumber);

    void delete(ClinicalRecord clinicalRecord);
}
