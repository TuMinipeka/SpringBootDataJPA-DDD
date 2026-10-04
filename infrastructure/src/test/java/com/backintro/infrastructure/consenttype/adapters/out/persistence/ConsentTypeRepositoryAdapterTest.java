package com.backintro.infrastructure.consenttype.adapters.out.persistence;

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

import com.backintro.domain.consenttype.model.aggregate.ConsentType;
import com.backintro.infrastructure.consenttype.adapters.out.persistence.entity.ConsentTypeJpaEntity;
import com.backintro.infrastructure.consenttype.adapters.out.persistence.mapper.ConsentTypePersistenceMapper;
import com.backintro.infrastructure.consenttype.adapters.out.persistence.repository.ConsentTypeJpaRepository;

@ExtendWith(MockitoExtension.class)
class ConsentTypeRepositoryAdapterTest {

    @Mock
    private ConsentTypeJpaRepository springDataRepository;

    private ConsentTypeRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ConsentTypeRepositoryAdapter(
                springDataRepository,
                new ConsentTypePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ConsentType consentType = newConsentType();
        when(springDataRepository.findById(consentType.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ConsentTypeJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ConsentType saved = adapter.save(consentType);

        assertThat(saved.id()).isEqualTo(consentType.id());
        assertThat(saved.name()).isEqualTo("Informed consent");
        assertThat(saved.code()).isEqualTo("INFORMED");
        assertThat(saved.description())
                .isEqualTo("Informed consent documentation");
        verify(springDataRepository).save(any(ConsentTypeJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ConsentType consentType = newConsentType();

        adapter.delete(consentType);

        verify(springDataRepository)
                .deleteById(consentType.id().value());
    }

    private ConsentType newConsentType() {
        return ConsentType.register(
                "Informed consent",
                "INFORMED",
                "Informed consent documentation"
        );
    }
}
