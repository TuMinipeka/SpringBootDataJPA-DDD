package com.backintro.domain.mentalstatusexam.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.backintro.domain.mentalstatusexam.model.valueobject.MentalStatusExamId;

public interface MentalStatusExamRepository {

    MentalStatusExam save(MentalStatusExam mentalStatusExam);

    Optional<MentalStatusExam> findById(MentalStatusExamId id);

    List<MentalStatusExam> findAll();

    boolean existsByEncounterId(EncounterId encounterId);

    void delete(MentalStatusExam mentalStatusExam);
}
