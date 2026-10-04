package com.backintro.domain.consenttype.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.consenttype.model.aggregate.ConsentType;
import com.backintro.domain.consenttype.model.valueobject.ConsentTypeId;

public interface ConsentTypeRepository {

    ConsentType save(ConsentType consentType);

    Optional<ConsentType> findById(ConsentTypeId id);

    List<ConsentType> findAll();

    boolean existsByCode(String code);

    boolean existsByName(String name);

    void delete(ConsentType consentType);
}
