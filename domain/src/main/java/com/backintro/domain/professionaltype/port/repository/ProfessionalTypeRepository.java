package com.backintro.domain.professionaltype.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.professionaltype.model.aggregate.ProfessionalType;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;

public interface ProfessionalTypeRepository {

    ProfessionalType save(ProfessionalType professionalType);

    Optional<ProfessionalType> findById(ProfessionalTypeId id);

    List<ProfessionalType> findAll();

    boolean existsByName(String name);

    void delete(ProfessionalType professionalType);
}
