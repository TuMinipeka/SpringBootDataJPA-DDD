package com.backintro.infrastructure.contact.adapters.out.persistence;

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

import com.backintro.domain.contact.model.aggregate.Contact;
import com.backintro.infrastructure.contact.adapters.out.persistence.entity.ContactJpaEntity;
import com.backintro.infrastructure.contact.adapters.out.persistence.mapper.ContactPersistenceMapper;
import com.backintro.infrastructure.contact.adapters.out.persistence.repository.ContactJpaRepository;

@ExtendWith(MockitoExtension.class)
class ContactRepositoryAdapterTest {

    @Mock
    private ContactJpaRepository springDataRepository;

    private ContactRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ContactRepositoryAdapter(
                springDataRepository,
                new ContactPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        Contact contact = Contact.register(
                "Jane Doe",
                "jane@example.com",
                null,
                null
        );
        when(springDataRepository.findById(contact.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ContactJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Contact saved = adapter.save(contact);

        assertThat(saved.id()).isEqualTo(contact.id());
        assertThat(saved.fullName()).isEqualTo("Jane Doe");
        assertThat(saved.cityId()).isNull();
        verify(springDataRepository).save(any(ContactJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        Contact contact = Contact.register(
                "Jane Doe",
                null,
                null,
                null
        );

        adapter.delete(contact);

        verify(springDataRepository).deleteById(contact.id().value());
    }
}
