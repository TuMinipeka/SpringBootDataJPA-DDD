package com.backintro.infrastructure.clinicalrecord.adapters.out.persistence;

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

import com.backintro.domain.clinicalrecord.model.aggregate.ClinicalRecord;
import com.backintro.domain.clinicalrecordstatus.model.valueobject.ClinicalRecordStatusId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.entity.ClinicalRecordJpaEntity;
import com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.mapper.ClinicalRecordPersistenceMapper;
import com.backintro.infrastructure.clinicalrecord.adapters.out.persistence.repository.ClinicalRecordJpaRepository;

@ExtendWith(MockitoExtension.class)
class ClinicalRecordRepositoryAdapterTest {

    @Mock
    private ClinicalRecordJpaRepository springDataRepository;

    private ClinicalRecordRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ClinicalRecordRepositoryAdapter(
                springDataRepository,
                new ClinicalRecordPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ClinicalRecord clinicalRecord = ClinicalRecord.register(
                new PatientId(UUID.randomUUID()),
                "CR-100",
                new ClinicalRecordStatusId(UUID.randomUUID())
        );
        when(springDataRepository.findById(clinicalRecord.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ClinicalRecordJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ClinicalRecord saved = adapter.save(clinicalRecord);

        assertThat(saved.id()).isEqualTo(clinicalRecord.id());
        assertThat(saved.patientId()).isEqualTo(clinicalRecord.patientId());
        assertThat(saved.recordNumber()).isEqualTo("CR-100");
        assertThat(saved.statusId()).isEqualTo(clinicalRecord.statusId());
        verify(springDataRepository).save(any(ClinicalRecordJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ClinicalRecord clinicalRecord = ClinicalRecord.register(
                new PatientId(UUID.randomUUID()),
                "CR-100",
                new ClinicalRecordStatusId(UUID.randomUUID())
        );

        adapter.delete(clinicalRecord);

        verify(springDataRepository)
                .deleteById(clinicalRecord.id().value());
    }
}
