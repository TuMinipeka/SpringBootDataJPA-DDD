package com.backintro.infrastructure.country.adapters.out.persistence;

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

import com.backintro.domain.country.model.aggregate.Country;
import com.backintro.infrastructure.country.adapters.out.persistence.entity.CountryJpaEntity;
import com.backintro.infrastructure.country.adapters.out.persistence.mapper.CountryPersistenceMapper;
import com.backintro.infrastructure.country.adapters.out.persistence.repository.CountryJpaRepository;

@ExtendWith(MockitoExtension.class)
class CountryRepositoryAdapterTest {

    @Mock
    private CountryJpaRepository springDataRepository;

    private CountryRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new CountryRepositoryAdapter(
                springDataRepository,
                new CountryPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        Country country = Country.register("Colombia", "CO");
        when(springDataRepository.findById(country.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(CountryJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Country saved = adapter.save(country);

        assertThat(saved.id()).isEqualTo(country.id());
        assertThat(saved.name()).isEqualTo("Colombia");
        assertThat(saved.code()).isEqualTo("CO");
        verify(springDataRepository).save(any(CountryJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        Country country = Country.register("Colombia", "CO");

        adapter.delete(country);

        verify(springDataRepository).deleteById(country.id().value());
    }
}
