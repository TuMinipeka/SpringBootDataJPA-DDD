package com.backintro.infrastructure.patient.adapters.out.persistence;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.backintro.domain.documenttype.model.valueobject.DocumentTypeId;
import com.backintro.domain.patient.model.aggregate.Patient;
import com.backintro.infrastructure.patient.adapters.out.persistence.entity.PatientJpaEntity;
import com.backintro.infrastructure.patient.adapters.out.persistence.mapper.PatientPersistenceMapper;
import com.backintro.infrastructure.patient.adapters.out.persistence.repository.PatientJpaRepository;

@ExtendWith(MockitoExtension.class)
class PatientRepositoryAdapterTest {

    @Mock
    private PatientJpaRepository springDataRepository;

    private PatientRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new PatientRepositoryAdapter(
                springDataRepository,
                new PatientPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        Patient patient = createPatient();
        when(springDataRepository.findById(patient.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(PatientJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Patient saved = adapter.save(patient);

        assertThat(saved.id()).isEqualTo(patient.id());
        assertThat(saved.documentNumber()).isEqualTo("123456789");
        assertThat(saved.firstName()).isEqualTo("Jane");
        assertThat(saved.lastName()).isEqualTo("Doe");
        assertThat(saved.active()).isTrue();
        verify(springDataRepository).save(any(PatientJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        Patient patient = createPatient();

        adapter.delete(patient);

        verify(springDataRepository).deleteById(patient.id().value());
    }

    private Patient createPatient() {
        return Patient.register(
                new DocumentTypeId(UUID.randomUUID()),
                "123456789",
                "Jane",
                null,
                "Doe",
                null,
                LocalDate.of(1990, 1, 15),
                null,
                null,
                null,
                null,
                null,
                null
        );
    }
}
