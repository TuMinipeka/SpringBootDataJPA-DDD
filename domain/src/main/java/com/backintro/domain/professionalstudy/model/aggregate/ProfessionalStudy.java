package com.backintro.domain.professionalstudy.model.aggregate;

import java.time.LocalDateTime;
import java.util.Objects;

import com.backintro.domain.common.model.AggregateRoot;
import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionalstudy.event.ProfessionalStudyRegisteredEvent;
import com.backintro.domain.professionalstudy.event.ProfessionalStudyUpdatedEvent;
import com.backintro.domain.professionalstudy.model.valueobject.ProfessionalStudyId;
import com.backintro.domain.study.model.valueobject.StudyId;

public class ProfessionalStudy extends AggregateRoot {

    private final ProfessionalStudyId id;
    private final StudyId studyId;
    private final ProfessionalId professionalId;
    private final CountryId countryId;
    private String title;
    private String university;
    private boolean valid;
    private String resolutionNumber;

    private ProfessionalStudy(
            ProfessionalStudyId id,
            StudyId studyId,
            ProfessionalId professionalId,
            String title,
            String university,
            boolean valid,
            String resolutionNumber,
            CountryId countryId
    ) {
        this.id = Objects.requireNonNull(id, "id must not be null");
        this.studyId = Objects.requireNonNull(
                studyId,
                "studyId must not be null"
        );
        this.professionalId = Objects.requireNonNull(
                professionalId,
                "professionalId must not be null"
        );
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.university = university;
        this.valid = valid;
        this.resolutionNumber = resolutionNumber;
        this.countryId = countryId;
    }

    public static ProfessionalStudy register(
            StudyId studyId,
            ProfessionalId professionalId,
            String title,
            String university,
            String resolutionNumber,
            CountryId countryId
    ) {
        ProfessionalStudyId id = ProfessionalStudyId.generate();
        ProfessionalStudy professionalStudy = new ProfessionalStudy(
                id,
                studyId,
                professionalId,
                title,
                university,
                true,
                resolutionNumber,
                countryId
        );

        professionalStudy.recordEvent(
                new ProfessionalStudyRegisteredEvent(
                        id,
                        LocalDateTime.now()
                )
        );

        return professionalStudy;
    }

    public static ProfessionalStudy restore(
            ProfessionalStudyId id,
            StudyId studyId,
            ProfessionalId professionalId,
            String title,
            String university,
            boolean valid,
            String resolutionNumber,
            CountryId countryId
    ) {
        return new ProfessionalStudy(
                id,
                studyId,
                professionalId,
                title,
                university,
                valid,
                resolutionNumber,
                countryId
        );
    }

    public void update(
            String title,
            String university,
            boolean valid,
            String resolutionNumber
    ) {
        this.title = Objects.requireNonNull(title, "title must not be null");
        this.university = university;
        this.valid = valid;
        this.resolutionNumber = resolutionNumber;

        recordEvent(
                new ProfessionalStudyUpdatedEvent(
                        this.id,
                        this.title,
                        this.university,
                        this.valid,
                        this.resolutionNumber,
                        LocalDateTime.now()
                )
        );
    }

    public ProfessionalStudyId id() {
        return id;
    }

    public StudyId studyId() {
        return studyId;
    }

    public ProfessionalId professionalId() {
        return professionalId;
    }

    public String title() {
        return title;
    }

    public String university() {
        return university;
    }

    public boolean valid() {
        return valid;
    }

    public String resolutionNumber() {
        return resolutionNumber;
    }

    public CountryId countryId() {
        return countryId;
    }
}
