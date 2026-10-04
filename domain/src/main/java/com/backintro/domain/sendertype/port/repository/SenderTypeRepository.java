package com.backintro.domain.sendertype.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.sendertype.model.aggregate.SenderType;
import com.backintro.domain.sendertype.model.valueobject.SenderTypeId;

public interface SenderTypeRepository {

    SenderType save(SenderType senderType);

    Optional<SenderType> findById(SenderTypeId id);

    List<SenderType> findAll();

    boolean existsByNameType(String nameType);

    void delete(SenderType senderType);
}
