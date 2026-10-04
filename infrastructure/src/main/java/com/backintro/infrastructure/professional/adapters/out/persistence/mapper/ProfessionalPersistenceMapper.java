package com.backintro.infrastructure.professional.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.professional.model.aggregate.Professional;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionaltype.model.valueobject.ProfessionalTypeId;
import com.backintro.infrastructure.professional.adapters.out.persistence.entity.ProfessionalJpaEntity;

@Component
public class ProfessionalPersistenceMapper {

    public Professional toDomain(ProfessionalJpaEntity entity) {
        return Professional.restore(
                new ProfessionalId(entity.getId()),
                new DocumentTypeId(entity.getDocumentTypeId()),
                entity.getDocumentNumber(),
                entity.getFirstName(),
                entity.getLastName(),
                new ProfessionalTypeId(entity.getProfessionalTypeId()),
                entity.getLicenseNumber(),
                entity.isActive(),
                entity.getCityId() == null
                        ? null
                        : new CityMunicipalityId(entity.getCityId()),
                entity.getContactId() == null
                        ? null
                        : new ContactId(entity.getContactId())
        );
    }

    public ProfessionalJpaEntity toNewEntity(Professional professional) {
        return new ProfessionalJpaEntity(
                professional.id().value(),
                professional.documentTypeId().value(),
                professional.documentNumber(),
                professional.firstName(),
                professional.lastName(),
                professional.professionalTypeId().value(),
                professional.licenseNumber(),
                professional.active(),
                professional.cityId() == null
                        ? null
                        : professional.cityId().value(),
                professional.contactId() == null
                        ? null
                        : professional.contactId().value()
        );
    }

    public void synchronize(
            Professional professional,
            ProfessionalJpaEntity entity
    ) {
        entity.synchronize(
                professional.documentNumber(),
                professional.firstName(),
                professional.lastName(),
                professional.licenseNumber(),
                professional.active()
        );
    }
}
