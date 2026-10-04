package com.backintro.infrastructure.country.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.country.model.aggregate.Country;
import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;

@Component
public class CountryPersistenceMapper {

    public Country toDomain(CountryJpaEntity entity) {
        return Country.restore(
                new CountryId(entity.getId()),
                entity.getName(),
                entity.getCode(),
                entity.isActive()
        );
    }

    public CountryJpaEntity toNewEntity(Country country) {
        return new CountryJpaEntity(
                country.id().value(),
                country.name(),
                country.code(),
                country.active()
        );
    }

    public void synchronize(Country country, CountryJpaEntity entity) {
        entity.synchronize(country.name(), country.code(), country.active());
    }
}
