package com.backintro.infrastructure.patientallergy.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientallergy.model.aggregate.PatientAllergy;
import com.backintro.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;

@Component
public class PatientAllergyPersistenceMapper {

    public PatientAllergy toDomain(PatientAllergyJpaEntity entity) {
        return PatientAllergy.restore(
                new PatientAllergyId(entity.getId()),
                new PatientId(entity.getPatientId()),
                entity.getSubstance(),
                entity.getReaction(),
                entity.getSeverity(),
                entity.isActive(),
                entity.getRecordedBy() == null
                        ? null
                        : new ProfessionalId(entity.getRecordedBy())
        );
    }

    public PatientAllergyJpaEntity toNewEntity(PatientAllergy allergy) {
        return new PatientAllergyJpaEntity(
                allergy.id().value(),
                allergy.patientId().value(),
                allergy.substance(),
                allergy.reaction(),
                allergy.severity(),
                allergy.active(),
                allergy.recordedBy() == null
                        ? null
                        : allergy.recordedBy().value()
        );
    }

    public void synchronize(
            PatientAllergy allergy,
            PatientAllergyJpaEntity entity
    ) {
        entity.synchronize(
                allergy.substance(),
                allergy.reaction(),
                allergy.severity(),
                allergy.active()
        );
    }
}
