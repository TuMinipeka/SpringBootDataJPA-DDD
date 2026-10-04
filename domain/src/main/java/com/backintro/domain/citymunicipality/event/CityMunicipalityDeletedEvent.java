package com.backintro.domain.citymunicipality.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.common.event.DomainEvent;

public record CityMunicipalityDeletedEvent(
        CityMunicipalityId id,
        LocalDateTime occurredOn
) implements DomainEvent {

    public CityMunicipalityDeletedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
