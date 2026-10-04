package com.backintro.infrastructure.clinicalnote.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.backintro.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;

@Component
public class ClinicalNotePersistenceMapper {

    public ClinicalNote toDomain(ClinicalNoteJpaEntity entity) {
        return ClinicalNote.restore(
                new ClinicalNoteId(entity.getId()),
                new EncounterId(entity.getEncounterId()),
                new ProfessionalId(entity.getProfessionalId()),
                entity.getSubjective(),
                entity.getObjective(),
                entity.getAssessment(),
                entity.getPlan(),
                entity.getAdditionalNotes(),
                entity.getSignedAt()
        );
    }

    public ClinicalNoteJpaEntity toNewEntity(ClinicalNote clinicalNote) {
        return new ClinicalNoteJpaEntity(
                clinicalNote.id().value(),
                clinicalNote.encounterId().value(),
                clinicalNote.professionalId().value(),
                clinicalNote.subjective(),
                clinicalNote.objective(),
                clinicalNote.assessment(),
                clinicalNote.plan(),
                clinicalNote.additionalNotes(),
                clinicalNote.signedAt()
        );
    }

    public void synchronize(
            ClinicalNote clinicalNote,
            ClinicalNoteJpaEntity entity
    ) {
        entity.synchronize(
                clinicalNote.subjective(),
                clinicalNote.objective(),
                clinicalNote.assessment(),
                clinicalNote.plan(),
                clinicalNote.additionalNotes(),
                clinicalNote.signedAt()
        );
    }
}
