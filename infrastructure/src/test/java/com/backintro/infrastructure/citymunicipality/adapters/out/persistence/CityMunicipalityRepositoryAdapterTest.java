package com.backintro.infrastructure.citymunicipality.adapters.out.persistence;

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

import com.backintro.domain.citymunicipality.model.aggregate.CityMunicipality;
import com.backintro.domain.stateregion.model.valueobject.StateRegionId;
import com.backintro.infrastructure.citymunicipality.adapters.out.persistence.entity.CityMunicipalityJpaEntity;
import com.backintro.infrastructure.citymunicipality.adapters.out.persistence.mapper.CityMunicipalityPersistenceMapper;
import com.backintro.infrastructure.citymunicipality.adapters.out.persistence.repository.CityMunicipalityJpaRepository;

@ExtendWith(MockitoExtension.class)
class CityMunicipalityRepositoryAdapterTest {

    @Mock
    private CityMunicipalityJpaRepository springDataRepository;

    private CityMunicipalityRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new CityMunicipalityRepositoryAdapter(
                springDataRepository,
                new CityMunicipalityPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        CityMunicipality cityMunicipality = CityMunicipality.register(
                new StateRegionId(UUID.randomUUID()),
                "Medellin",
                "MDE"
        );
        when(springDataRepository.findById(cityMunicipality.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(CityMunicipalityJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        CityMunicipality saved = adapter.save(cityMunicipality);

        assertThat(saved.id()).isEqualTo(cityMunicipality.id());
        assertThat(saved.regionId()).isEqualTo(cityMunicipality.regionId());
        assertThat(saved.nameCity()).isEqualTo("Medellin");
        assertThat(saved.codeCity()).isEqualTo("MDE");
        verify(springDataRepository).save(any(CityMunicipalityJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        CityMunicipality cityMunicipality = CityMunicipality.register(
                new StateRegionId(UUID.randomUUID()),
                "Medellin",
                "MDE"
        );

        adapter.delete(cityMunicipality);

        verify(springDataRepository)
                .deleteById(cityMunicipality.id().value());
    }
}
