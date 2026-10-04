package com.backintro.infrastructure.contact.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.contact.model.aggregate.Contact;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;

@Component
public class ContactPersistenceMapper {

    public Contact toDomain(ContactJpaEntity entity) {
        return Contact.restore(
                new ContactId(entity.getId()),
                entity.getFullName(),
                entity.getEmail(),
                entity.getNotes(),
                entity.getCityId() == null
                        ? null
                        : new CityMunicipalityId(entity.getCityId())
        );
    }

    public ContactJpaEntity toNewEntity(Contact contact) {
        return new ContactJpaEntity(
                contact.id().value(),
                contact.fullName(),
                contact.email(),
                contact.notes(),
                contact.cityId() == null ? null : contact.cityId().value()
        );
    }

    public void synchronize(Contact contact, ContactJpaEntity entity) {
        entity.synchronize(
                contact.fullName(),
                contact.email(),
                contact.notes(),
                contact.cityId() == null ? null : contact.cityId().value()
        );
    }
}
