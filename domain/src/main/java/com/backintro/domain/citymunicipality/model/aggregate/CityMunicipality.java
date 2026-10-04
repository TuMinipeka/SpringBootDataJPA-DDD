package com.backintro.domain.citymunicipality.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.citymunicipality.event.CityMunicipalityRegisteredEvent;
import com.backintro.domain.citymunicipality.event.CityMunicipalityUpdatedEvent;
import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;

public class CityMunicipality extends AggregateRoot {

    private final CityMunicipalityId id;
    private final StateRegionId regionId;
    private String nameCity;
    private String codeCity;
    private boolean active;

    private CityMunicipality(
            CityMunicipalityId id,
            StateRegionId regionId,
            String nameCity,
            String codeCity,
            boolean active
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.regionId = Objects.requireNonNull(regionId, "regionId must not be null");
        this.nameCity = Objects.requireNonNull(nameCity, "nameCity must not be null");
        this.codeCity = Objects.requireNonNull(codeCity, "codeCity must not be null");
        this.active = active;
    }

    public static CityMunicipality register(
            StateRegionId regionId,
            String nameCity,
            String codeCity
    ) {
        CityMunicipalityId id = CityMunicipalityId.generate();

        CityMunicipality cityMunicipality = new CityMunicipality(
                id,
                regionId,
                nameCity,
                codeCity,
                true
        );

        cityMunicipality.recordEvent(
                new CityMunicipalityRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return cityMunicipality;
    }

    public static CityMunicipality restore(
            CityMunicipalityId id,
            StateRegionId regionId,
            String nameCity,
            String codeCity,
            boolean active
    ) {
        return new CityMunicipality(
                id,
                regionId,
                nameCity,
                codeCity,
                active
        );
    }

    public void update(
            String nameCity,
            String codeCity
    ) {
        this.nameCity = Objects.requireNonNull(nameCity, "nameCity must not be null");
        this.codeCity = Objects.requireNonNull(codeCity, "codeCity must not be null");

        recordEvent(
                new CityMunicipalityUpdatedEvent(
                        this.id,
                        this.nameCity,
                        this.codeCity,
                        LocalDateTime.now()
                )
        );
    }

    public CityMunicipalityId id() {
        return id;
    }

    public StateRegionId regionId() {
        return regionId;
    }

    public String nameCity() {
        return nameCity;
    }

    public String codeCity() {
        return codeCity;
    }

    public boolean active() {
        return active;
    }
}
