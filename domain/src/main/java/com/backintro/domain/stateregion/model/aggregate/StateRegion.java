package com.backintro.domain.stateregion.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.stateregion.event.StateRegionRegisteredEvent;
import com.backintro.domain.stateregion.event.StateRegionUpdatedEvent;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

public class StateRegion extends AggregateRoot {

    private final StateRegionId id;
    private final CountryId countryId;
    private String nameRegion;
    private String codeRegion;
    private boolean active;

    private StateRegion(
            StateRegionId id,
            CountryId countryId,
            String nameRegion,
            String codeRegion,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.countryId = Objects.requireNonNull(countryId, "countryId must not be null");
        this.nameRegion = Objects.requireNonNull(nameRegion, "nameRegion must not be null");
        this.codeRegion = Objects.requireNonNull(codeRegion, "codeRegion must not be null");
        this.active = active;
    }

    public static StateRegion register(
            CountryId countryId,
            String nameRegion,
            String codeRegion
    ) {
        StateRegionId id = StateRegionId.generate();

        StateRegion stateRegion = new StateRegion(
                id,
                countryId,
                nameRegion,
                codeRegion,
                true
        );

        stateRegion.recordEvent(
                new StateRegionRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return stateRegion;
    }

    public static StateRegion restore(
            StateRegionId id,
            CountryId countryId,
            String nameRegion,
            String codeRegion,
            boolean active
    ) {
        return new StateRegion(
                id,
                countryId,
                nameRegion,
                codeRegion,
                active
        );
    }

    public void update(
            String nameRegion,
            String codeRegion
    ) {
        this.nameRegion = Objects.requireNonNull(nameRegion, "nameRegion must not be null");
        this.codeRegion = Objects.requireNonNull(codeRegion, "codeRegion must not be null");

        recordEvent(
                new StateRegionUpdatedEvent(
                        this.id,
                        this.nameRegion,
                        this.codeRegion,
                        LocalDateTime.now()
                )
        );
    }

    public StateRegionId id() {
        return id;
    }

    public CountryId countryId() {
        return countryId;
    }

    public String nameRegion() {
        return nameRegion;
    }

    public String codeRegion() {
        return codeRegion;
    }

    public boolean active() {
        return active;
    }
}
