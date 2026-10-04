package com.backintro.domain.patientallergy.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientallergy.event.PatientAllergyRegisteredEvent;
import com.backintro.domain.patientallergy.event.PatientAllergyUpdatedEvent;
import com.backintro.domain.patientallergy.model.valueobject.PatientAllergyId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public class PatientAllergy extends AggregateRoot {

    private final PatientAllergyId id;
    private final PatientId patientId;
    private String substance;
    private String reaction;
    private String severity;
    private boolean active;
    private final ProfessionalId recordedBy;

    private PatientAllergy(
            PatientAllergyId id,
            PatientId patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            ProfessionalId recordedBy
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.patientId = Objects.requireNonNull(
                patientId,
                "patientId must not be null"
        );
        this.substance = Objects.requireNonNull(
                substance,
                "substance must not be null"
        );
        this.reaction = reaction;
        this.severity = severity;
        this.active = active;
        this.recordedBy = recordedBy;
    }

    public static PatientAllergy register(
            PatientId patientId,
            String substance,
            String reaction,
            String severity,
            ProfessionalId recordedBy
    ) {
        PatientAllergyId id = PatientAllergyId.generate();
        PatientAllergy allergy = new PatientAllergy(
                id,
                patientId,
                substance,
                reaction,
                severity,
                true,
                recordedBy
        );

        allergy.recordEvent(
                new PatientAllergyRegisteredEvent(id, LocalDateTime.now())
        );

        return allergy;
    }

    public static PatientAllergy restore(
            PatientAllergyId id,
            PatientId patientId,
            String substance,
            String reaction,
            String severity,
            boolean active,
            ProfessionalId recordedBy
    ) {
        return new PatientAllergy(
                id,
                patientId,
                substance,
                reaction,
                severity,
                active,
                recordedBy
        );
    }

    public void update(
            String substance,
            String reaction,
            String severity,
            boolean active
    ) {
        this.substance = Objects.requireNonNull(
                substance,
                "substance must not be null"
        );
        this.reaction = reaction;
        this.severity = severity;
        this.active = active;

        recordEvent(
                new PatientAllergyUpdatedEvent(
                        this.id,
                        this.substance,
                        this.reaction,
                        this.severity,
                        this.active,
                        LocalDateTime.now()
                )
        );
    }

    public PatientAllergyId id() {
        return id;
    }

    public PatientId patientId() {
        return patientId;
    }

    public String substance() {
        return substance;
    }

    public String reaction() {
        return reaction;
    }

    public String severity() {
        return severity;
    }

    public boolean active() {
        return active;
    }

    public ProfessionalId recordedBy() {
        return recordedBy;
    }
}
