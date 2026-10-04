package com.backintro.infrastructure.patient.adapters.out.persistence.mapper;

import java.util.UUID;

import org.springframework.stereotype.Component;

import com.backintro.domain.citymunicipality.model.valueobject.CityMunicipalityId;
import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.gender.model.valueobject.GenderId;
import com.backintro.domain.patient.model.aggregate.Patient;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;

@Component
public class PatientPersistenceMapper {

    public Patient toDomain(PatientJpaEntity entity) {
        return Patient.restore(
                new PatientId(entity.getId()),
                new DocumentTypeId(entity.getDocumentTypeId()),
                entity.getDocumentNumber(),
                entity.getFirstName(),
                entity.getMiddleName(),
                entity.getLastName(),
                entity.getSecondLastName(),
                entity.getBirthDate(),
                toGenderId(entity.getBiologicalSexId()),
                toGenderId(entity.getGenderIdentityId()),
                entity.getEmail(),
                entity.getPhone(),
                entity.getAddress(),
                entity.isActive(),
                entity.getCityId() == null
                        ? null
                        : new CityMunicipalityId(entity.getCityId())
        );
    }

    public PatientJpaEntity toNewEntity(Patient patient) {
        return new PatientJpaEntity(
                patient.id().value(),
                patient.documentTypeId().value(),
                patient.documentNumber(),
                patient.firstName(),
                patient.middleName(),
                patient.lastName(),
                patient.secondLastName(),
                patient.birthDate(),
                patient.biologicalSexId() == null
                        ? null
                        : patient.biologicalSexId().value(),
                patient.genderIdentityId() == null
                        ? null
                        : patient.genderIdentityId().value(),
                patient.email(),
                patient.phone(),
                patient.address(),
                patient.active(),
                patient.cityId() == null ? null : patient.cityId().value()
        );
    }

    public void synchronize(Patient patient, PatientJpaEntity entity) {
        entity.synchronize(
                patient.documentNumber(),
                patient.firstName(),
                patient.middleName(),
                patient.lastName(),
                patient.secondLastName(),
                patient.birthDate(),
                patient.email(),
                patient.phone(),
                patient.address(),
                patient.active()
        );
    }

    private GenderId toGenderId(UUID value) {
        return value == null ? null : new GenderId(value);
    }
}
