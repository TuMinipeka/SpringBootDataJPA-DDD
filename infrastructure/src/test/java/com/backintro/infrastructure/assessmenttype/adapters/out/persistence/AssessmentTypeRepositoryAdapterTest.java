package com.backintro.infrastructure.assessmenttype.adapters.out.persistence;

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

import com.backintro.domain.assessmenttype.model.aggregate.AssessmentType;
import com.backintro.infrastructure.assessmenttype.adapters.out.persistence.entity.AssessmentTypeJpaEntity;
import com.backintro.infrastructure.assessmenttype.adapters.out.persistence.mapper.AssessmentTypePersistenceMapper;
import com.backintro.infrastructure.assessmenttype.adapters.out.persistence.repository.AssessmentTypeJpaRepository;

@ExtendWith(MockitoExtension.class)
class AssessmentTypeRepositoryAdapterTest {

    @Mock
    private AssessmentTypeJpaRepository springDataRepository;

    private AssessmentTypeRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new AssessmentTypeRepositoryAdapter(
                springDataRepository,
                new AssessmentTypePersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        AssessmentType assessmentType = newAssessmentType();
        when(springDataRepository.findById(assessmentType.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(AssessmentTypeJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        AssessmentType saved = adapter.save(assessmentType);

        assertThat(saved.id()).isEqualTo(assessmentType.id());
        assertThat(saved.name()).isEqualTo("Psychological");
        assertThat(saved.code()).isEqualTo("PSY");
        assertThat(saved.description())
                .isEqualTo("Psychological assessment");
        verify(springDataRepository).save(any(AssessmentTypeJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        AssessmentType assessmentType = newAssessmentType();

        adapter.delete(assessmentType);

        verify(springDataRepository)
                .deleteById(assessmentType.id().value());
    }

    private AssessmentType newAssessmentType() {
        return AssessmentType.register(
                "Psychological",
                "PSY",
                "Psychological assessment"
        );
    }
}
