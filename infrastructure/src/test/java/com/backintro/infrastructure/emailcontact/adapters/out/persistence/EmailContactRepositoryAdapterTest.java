package com.backintro.infrastructure.emailcontact.adapters.out.persistence;

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
import com.backintro.domain.emailcontact.model.aggregate.EmailContact;
import com.backintro.infrastructure.emailcontact.adapters.out.persistence.entity.EmailContactJpaEntity;
import com.backintro.infrastructure.emailcontact.adapters.out.persistence.mapper.EmailContactPersistenceMapper;
import com.backintro.infrastructure.emailcontact.adapters.out.persistence.repository.EmailContactJpaRepository;

@ExtendWith(MockitoExtension.class)
class EmailContactRepositoryAdapterTest {

    @Mock
    private EmailContactJpaRepository springDataRepository;

    private EmailContactRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new EmailContactRepositoryAdapter(
                springDataRepository,
                new EmailContactPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        EmailContact emailContact = EmailContact.register(
                new ContactId(UUID.randomUUID()),
                "jane@example.com",
                null
        );
        when(springDataRepository.findById(emailContact.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(EmailContactJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        EmailContact saved = adapter.save(emailContact);

        assertThat(saved.id()).isEqualTo(emailContact.id());
        assertThat(saved.contactId()).isEqualTo(emailContact.contactId());
        assertThat(saved.email()).isEqualTo("jane@example.com");
        verify(springDataRepository).save(any(EmailContactJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        EmailContact emailContact = EmailContact.register(
                new ContactId(UUID.randomUUID()),
                "jane@example.com",
                null
        );

        adapter.delete(emailContact);

        verify(springDataRepository)
                .deleteById(emailContact.id().value());
    }
}
