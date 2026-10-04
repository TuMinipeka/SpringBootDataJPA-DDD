package com.backintro.domain.citymunicipality.event;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.common.event.DomainEvent;

public record CityMunicipalityUpdatedEvent(
        CityMunicipalityId id,
        String nameCity,
        String codeCity,
        LocalDateTime occurredOn
) implements DomainEvent {

    public CityMunicipalityUpdatedEvent {
        Objects.requireNonNull(id, "id must not be null");
        Objects.requireNonNull(nameCity, "nameCity must not be null");
        Objects.requireNonNull(codeCity, "codeCity must not be null");
        Objects.requireNonNull(occurredOn, "occurredOn must not be null");
    }
}
