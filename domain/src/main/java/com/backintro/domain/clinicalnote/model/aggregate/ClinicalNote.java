package com.backintro.domain.clinicalnote.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.clinicalnote.event.ClinicalNoteRegisteredEvent;
import com.backintro.domain.clinicalnote.event.ClinicalNoteUpdatedEvent;
import com.backintro.domain.clinicalnote.model.valueobject.ClinicalNoteId;
import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public class ClinicalNote extends AggregateRoot {

    private final ClinicalNoteId id;
    private final EncounterId encounterId;
    private final ProfessionalId professionalId;
    private String subjective;
    private String objective;
    private String assessment;
    private String plan;
    private String additionalNotes;
    private LocalDateTime signedAt;

    private ClinicalNote(
            ClinicalNoteId id,
            EncounterId encounterId,
            ProfessionalId professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.encounterId = Objects.requireNonNull(
                encounterId,
                "encounterId must not be null"
        );
        this.professionalId = Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
        this.subjective = subjective;
        this.objective = objective;
        this.assessment = assessment;
        this.plan = plan;
        this.additionalNotes = additionalNotes;
        this.signedAt = signedAt;
    }

    public static ClinicalNote register(
            EncounterId encounterId,
            ProfessionalId professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes
    ) {
        ClinicalNoteId id = ClinicalNoteId.generate();
        ClinicalNote clinicalNote = new ClinicalNote(
                id,
                encounterId,
                professionalId,
                subjective,
                objective,
                assessment,
                plan,
                additionalNotes,
                null
        );

        clinicalNote.recordEvent(
                new ClinicalNoteRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return clinicalNote;
    }

    public static ClinicalNote restore(
            ClinicalNoteId id,
            EncounterId encounterId,
            ProfessionalId professionalId,
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt
    ) {
        return new ClinicalNote(
                id,
                encounterId,
                professionalId,
                subjective,
                objective,
                assessment,
                plan,
                additionalNotes,
                signedAt
        );
    }

    public void update(
            String subjective,
            String objective,
            String assessment,
            String plan,
            String additionalNotes,
            LocalDateTime signedAt
    ) {
        this.subjective = subjective;
        this.objective = objective;
        this.assessment = assessment;
        this.plan = plan;
        this.additionalNotes = additionalNotes;
        this.signedAt = signedAt;

        recordEvent(
                new ClinicalNoteUpdatedEvent(
                        this.id,
                        this.subjective,
                        this.objective,
                        this.assessment,
                        this.plan,
                        this.additionalNotes,
                        this.signedAt,
                        LocalDateTime.now()
                )
        );
    }

    public ClinicalNoteId id() {
        return id;
    }

    public EncounterId encounterId() {
        return encounterId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public String subjective() {
        return subjective;
    }

    public String objective() {
        return objective;
    }

    public String assessment() {
        return assessment;
    }

    public String plan() {
        return plan;
    }

    public String additionalNotes() {
        return additionalNotes;
    }

    public LocalDateTime signedAt() {
        return signedAt;
    }
}
