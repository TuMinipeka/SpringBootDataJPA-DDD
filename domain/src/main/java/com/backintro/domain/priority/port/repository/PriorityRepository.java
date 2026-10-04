package com.backintro.domain.priority.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.priority.model.aggregate.Priority;
import com.backintro.domain.priority.model.valueobject.PriorityId;

public interface PriorityRepository {

    Priority save(Priority priority);

    Optional<Priority> findById(PriorityId id);

    List<Priority> findAll();

    boolean existsByNamePriority(String namePriority);

    void delete(Priority priority);
}
