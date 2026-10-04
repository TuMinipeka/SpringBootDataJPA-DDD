package com.backintro.infrastructure.professionalstudy.adapters.out.persistence;

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

import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professionalstudy.model.aggregate.ProfessionalStudy;
import com.backintro.domain.study.model.valueobject.StudyId;
import com.backintro.infrastructure.professionalstudy.adapters.out.persistence.entity.ProfessionalStudyJpaEntity;
import com.backintro.infrastructure.professionalstudy.adapters.out.persistence.mapper.ProfessionalStudyPersistenceMapper;
import com.backintro.infrastructure.professionalstudy.adapters.out.persistence.repository.ProfessionalStudyJpaRepository;

@ExtendWith(MockitoExtension.class)
class ProfessionalStudyRepositoryAdapterTest {

    @Mock
    private ProfessionalStudyJpaRepository springDataRepository;

    private ProfessionalStudyRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ProfessionalStudyRepositoryAdapter(
                springDataRepository,
                new ProfessionalStudyPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ProfessionalStudy professionalStudy = ProfessionalStudy.register(
                new StudyId(UUID.randomUUID()),
                new ProfessionalId(UUID.randomUUID()),
                "Clinical Psychology",
                null,
                null,
                null
        );
        when(springDataRepository.findById(professionalStudy.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(ProfessionalStudyJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        ProfessionalStudy saved = adapter.save(professionalStudy);

        assertThat(saved.id()).isEqualTo(professionalStudy.id());
        assertThat(saved.studyId()).isEqualTo(professionalStudy.studyId());
        assertThat(saved.professionalId())
                .isEqualTo(professionalStudy.professionalId());
        assertThat(saved.title()).isEqualTo("Clinical Psychology");
        assertThat(saved.valid()).isTrue();
        verify(springDataRepository)
                .save(any(ProfessionalStudyJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ProfessionalStudy professionalStudy = ProfessionalStudy.register(
                new StudyId(UUID.randomUUID()),
                new ProfessionalId(UUID.randomUUID()),
                "Clinical Psychology",
                null,
                null,
                null
        );

        adapter.delete(professionalStudy);

        verify(springDataRepository)
                .deleteById(professionalStudy.id().value());
    }
}
