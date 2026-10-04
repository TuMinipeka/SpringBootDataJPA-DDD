package com.backintro.infrastructure.professionalstudy.adapters.out.persistence.mapper;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.backintro.domain.study.model.valueobject.StudyId;

class ProfessionalStudyPersistenceMapperTest {

    private final ProfessionalStudyPersistenceMapper mapper =
            new ProfessionalStudyPersistenceMapper();

    @Test
    void mapsProfessionalStudyInBothDirectionsWithoutCreatingDomainEvents() {
        StudyId studyId = new StudyId(UUID.randomUUID());
        ProfessionalId professionalId =
                new ProfessionalId(UUID.randomUUID());
        CountryId countryId = new CountryId(UUID.randomUUID());
        ProfessionalStudy original = ProfessionalStudy.register(
                studyId,
                professionalId,
                "Clinical Psychology",
                "National University",
                "RES-100",
                countryId
        );

        ProfessionalStudy restored =
                mapper.toDomain(mapper.toNewEntity(original));

        assertThat(restored.id()).isEqualTo(original.id());
        assertThat(restored.studyId()).isEqualTo(studyId);
        assertThat(restored.professionalId()).isEqualTo(professionalId);
        assertThat(restored.title()).isEqualTo("Clinical Psychology");
        assertThat(restored.university()).isEqualTo("National University");
        assertThat(restored.valid()).isTrue();
        assertThat(restored.resolutionNumber()).isEqualTo("RES-100");
        assertThat(restored.countryId()).isEqualTo(countryId);
        assertThat(restored.domainEvents()).isEmpty();
    }
}
