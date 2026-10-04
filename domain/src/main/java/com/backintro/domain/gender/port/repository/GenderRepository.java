package com.backintro.domain.gender.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.gender.model.aggregate.Gender;
import com.backintro.domain.gender.model.valueobject.GenderId;

public interface GenderRepository {

    Gender save(Gender gender);

    Optional<Gender> findById(GenderId id);

    List<Gender> findAll();

    boolean existsByDescription(String description);

    void delete(Gender gender);
}
