package com.backintro.infrastructure.patientcontact.adapters.out.persistence;

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

import com.backintro.domain.contact.model.valueobject.ContactId;
import com.backintro.domain.patient.model.valueobject.PatientId;
import com.backintro.domain.patientcontact.model.aggregate.PatientContact;
import com.backintro.domain.relationshiptype.model.valueobject.RelationshipTypeId;
import com.backintro.infrastructure.patientcontact.adapters.out.persistence.entity.PatientContactJpaEntity;
import com.backintro.infrastructure.patientcontact.adapters.out.persistence.mapper.PatientContactPersistenceMapper;
import com.backintro.infrastructure.patientcontact.adapters.out.persistence.repository.PatientContactJpaRepository;

@ExtendWith(MockitoExtension.class)
class PatientContactRepositoryAdapterTest {

    @Mock
    private PatientContactJpaRepository springDataRepository;

    private PatientContactRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new PatientContactRepositoryAdapter(
                springDataRepository,
                new PatientContactPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        PatientContact patientContact = PatientContact.register(
                new ContactId(UUID.randomUUID()),
                new PatientId(UUID.randomUUID()),
                true,
                false,
                new RelationshipTypeId(UUID.randomUUID())
        );
        when(springDataRepository.findById(patientContact.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(PatientContactJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PatientContact saved = adapter.save(patientContact);

        assertThat(saved.id()).isEqualTo(patientContact.id());
        assertThat(saved.contactId()).isEqualTo(patientContact.contactId());
        assertThat(saved.patientId()).isEqualTo(patientContact.patientId());
        assertThat(saved.primaryContact()).isTrue();
        assertThat(saved.emergencyContact()).isFalse();
        assertThat(saved.relationshipTypeId())
                .isEqualTo(patientContact.relationshipTypeId());
        verify(springDataRepository).save(any(PatientContactJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        PatientContact patientContact = PatientContact.register(
                new ContactId(UUID.randomUUID()),
                new PatientId(UUID.randomUUID()),
                true,
                false,
                new RelationshipTypeId(UUID.randomUUID())
        );

        adapter.delete(patientContact);

        verify(springDataRepository)
                .deleteById(patientContact.id().value());
    }
}
