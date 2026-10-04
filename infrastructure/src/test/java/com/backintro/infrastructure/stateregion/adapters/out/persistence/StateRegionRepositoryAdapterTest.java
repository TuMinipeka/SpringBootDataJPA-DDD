package com.backintro.infrastructure.stateregion.adapters.out.persistence;

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

import com.backintro.domain.country.model.valueobject.CountryId;
import com.backintro.domain.stateregion.model.aggregate.StateRegion;
import com.backintro.infrastructure.stateregion.adapters.out.persistence.entity.StateRegionJpaEntity;
import com.backintro.infrastructure.stateregion.adapters.out.persistence.mapper.StateRegionPersistenceMapper;
import com.backintro.infrastructure.stateregion.adapters.out.persistence.repository.StateRegionJpaRepository;

@ExtendWith(MockitoExtension.class)
class StateRegionRepositoryAdapterTest {

    @Mock
    private StateRegionJpaRepository springDataRepository;

    private StateRegionRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new StateRegionRepositoryAdapter(
                springDataRepository,
                new StateRegionPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        StateRegion stateRegion = StateRegion.register(
                new CountryId(UUID.randomUUID()),
                "Antioquia",
                "ANT"
        );
        when(springDataRepository.findById(stateRegion.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(StateRegionJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        StateRegion saved = adapter.save(stateRegion);

        assertThat(saved.id()).isEqualTo(stateRegion.id());
        assertThat(saved.countryId()).isEqualTo(stateRegion.countryId());
        assertThat(saved.nameRegion()).isEqualTo("Antioquia");
        assertThat(saved.codeRegion()).isEqualTo("ANT");
        verify(springDataRepository).save(any(StateRegionJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        StateRegion stateRegion = StateRegion.register(
                new CountryId(UUID.randomUUID()),
                "Antioquia",
                "ANT"
        );

        adapter.delete(stateRegion);

        verify(springDataRepository).deleteById(stateRegion.id().value());
    }
}
