package com.backintro.infrastructure.clinicalnote.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.clinicalnote.model.aggregate.ClinicalNote;
import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.clinicalnote.adapters.out.persistence.entity.ClinicalNoteJpaEntity;
import com.backintro.infrastructure.clinicalnote.adapters.out.persistence.mapper.ClinicalNotePersistenceMapper;
import com.backintro.infrastructure.clinicalnote.adapters.out.persistence.repository.ClinicalNoteJpaRepository;

@ExtendWith(MockitoExtension.class)
class ClinicalNoteRepositoryAdapterTest {

    @Mock
    private ClinicalNoteJpaRepository springDataRepository;

    private ClinicalNoteRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ClinicalNoteRepositoryAdapter(
                springDataRepository,
                new ClinicalNotePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ClinicalNote clinicalNote = newClinicalNote();
        when(springDataRepository.findById(clinicalNote.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ClinicalNoteJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ClinicalNote saved = adapter.save(clinicalNote);

        assertThat(saved.id()).isEqualTo(clinicalNote.id());
        assertThat(saved.encounterId()).isEqualTo(clinicalNote.encounterId());
        assertThat(saved.professionalId())
                .isEqualTo(clinicalNote.professionalId());
        assertThat(saved.subjective()).isEqualTo("Patient reports improvement");
        assertThat(saved.objective()).isEqualTo("Vital signs stable");
        assertThat(saved.assessment()).isEqualTo("Positive evolution");
        assertThat(saved.plan()).isEqualTo("Continue treatment");
        assertThat(saved.additionalNotes()).isEqualTo("Follow up in 30 days");
        assertThat(saved.signedAt()).isNull();
        verify(springDataRepository).save(any(ClinicalNoteJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ClinicalNote clinicalNote = newClinicalNote();

        adapter.delete(clinicalNote);

        verify(springDataRepository).deleteById(clinicalNote.id().value());
    }

    private ClinicalNote newClinicalNote() {
        return ClinicalNote.register(
                new EncounterId(UUID.randomUUID()),
                new ProfessionalId(UUID.randomUUID()),
                "Patient reports improvement",
                "Vital signs stable",
                "Positive evolution",
                "Continue treatment",
                "Follow up in 30 days"
        );
    }
}
