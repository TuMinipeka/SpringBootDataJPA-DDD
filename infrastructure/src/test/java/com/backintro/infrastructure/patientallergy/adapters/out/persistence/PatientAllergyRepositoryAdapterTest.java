package com.backintro.infrastructure.patientallergy.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientallergy.model.aggregate.PatientAllergy;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.patientallergy.adapters.out.persistence.entity.PatientAllergyJpaEntity;
import com.backintro.infrastructure.patientallergy.adapters.out.persistence.mapper.PatientAllergyPersistenceMapper;
import com.backintro.infrastructure.patientallergy.adapters.out.persistence.repository.PatientAllergyJpaRepository;

@ExtendWith(MockitoExtension.class)
class PatientAllergyRepositoryAdapterTest {

    @Mock
    private PatientAllergyJpaRepository springDataRepository;

    private PatientAllergyRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new PatientAllergyRepositoryAdapter(
                springDataRepository,
                new PatientAllergyPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        PatientAllergy allergy = newAllergy();
        when(springDataRepository.findById(allergy.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(PatientAllergyJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PatientAllergy saved = adapter.save(allergy);

        assertThat(saved.id()).isEqualTo(allergy.id());
        assertThat(saved.patientId()).isEqualTo(allergy.patientId());
        assertThat(saved.substance()).isEqualTo("Penicillin");
        assertThat(saved.reaction()).isEqualTo("Skin rash");
        assertThat(saved.severity()).isEqualTo("MODERATE");
        assertThat(saved.active()).isTrue();
        assertThat(saved.recordedBy()).isEqualTo(allergy.recordedBy());
        verify(springDataRepository).save(any(PatientAllergyJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        PatientAllergy allergy = newAllergy();

        adapter.delete(allergy);

        verify(springDataRepository).deleteById(allergy.id().value());
    }

    private PatientAllergy newAllergy() {
        return PatientAllergy.register(
                PatientId.generate(),
                "Penicillin",
                "Skin rash",
                "MODERATE",
                ProfessionalId.generate()
        );
    }
}
