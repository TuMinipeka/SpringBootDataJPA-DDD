package com.backintro.domain.study.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.study.model.aggregate.Study;
import com.backintro.domain.study.model.valueobject.StudyId;

public interface StudyRepository {

    Study save(Study study);

    Optional<Study> findById(StudyId id);

    List<Study> findAll();

    boolean existsByName(String name);

    void delete(Study study);
}
