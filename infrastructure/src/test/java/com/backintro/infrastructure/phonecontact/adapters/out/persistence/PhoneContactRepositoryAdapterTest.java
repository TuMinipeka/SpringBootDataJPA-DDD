package com.backintro.infrastructure.phonecontact.adapters.out.persistence;

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
import com.backintro.domain.phonecontact.model.aggregate.PhoneContact;
import com.backintro.infrastructure.phonecontact.adapters.out.persistence.entity.PhoneContactJpaEntity;
import com.backintro.infrastructure.phonecontact.adapters.out.persistence.mapper.PhoneContactPersistenceMapper;
import com.backintro.infrastructure.phonecontact.adapters.out.persistence.repository.PhoneContactJpaRepository;

@ExtendWith(MockitoExtension.class)
class PhoneContactRepositoryAdapterTest {

    @Mock
    private PhoneContactJpaRepository springDataRepository;

    private PhoneContactRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new PhoneContactRepositoryAdapter(
                springDataRepository,
                new PhoneContactPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        PhoneContact phoneContact = PhoneContact.register(
                new ContactId(UUID.randomUUID()),
                "+57 300 123 4567",
                null
        );
        when(springDataRepository.findById(phoneContact.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(PhoneContactJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        PhoneContact saved = adapter.save(phoneContact);

        assertThat(saved.id()).isEqualTo(phoneContact.id());
        assertThat(saved.contactId()).isEqualTo(phoneContact.contactId());
        assertThat(saved.phone()).isEqualTo("+57 300 123 4567");
        verify(springDataRepository).save(any(PhoneContactJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        PhoneContact phoneContact = PhoneContact.register(
                new ContactId(UUID.randomUUID()),
                "+57 300 123 4567",
                null
        );

        adapter.delete(phoneContact);

        verify(springDataRepository)
                .deleteById(phoneContact.id().value());
    }
}
