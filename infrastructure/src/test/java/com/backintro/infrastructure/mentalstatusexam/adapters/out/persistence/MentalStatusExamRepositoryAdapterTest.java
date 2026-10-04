package com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence;

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

import com.backintro.domain.encounter.model.valueobject.EncounterId;
import com.backintro.domain.mentalstatusexam.model.aggregate.MentalStatusExam;
import com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.entity.MentalStatusExamJpaEntity;
import com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.mapper.MentalStatusExamPersistenceMapper;
import com.backintro.infrastructure.mentalstatusexam.adapters.out.persistence.repository.MentalStatusExamJpaRepository;

@ExtendWith(MockitoExtension.class)
class MentalStatusExamRepositoryAdapterTest {

    @Mock
    private MentalStatusExamJpaRepository springDataRepository;

    private MentalStatusExamRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new MentalStatusExamRepositoryAdapter(
                springDataRepository,
                new MentalStatusExamPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        MentalStatusExam exam = newMentalStatusExam();
        when(springDataRepository.findById(exam.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(MentalStatusExamJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        MentalStatusExam saved = adapter.save(exam);

        assertThat(saved.id()).isEqualTo(exam.id());
        assertThat(saved.encounterId()).isEqualTo(exam.encounterId());
        assertThat(saved.appearance()).isEqualTo("Neat");
        assertThat(saved.mood()).isEqualTo("Euthymic");
        assertThat(saved.thoughtProcess()).isEqualTo("Logical");
        assertThat(saved.observations())
                .isEqualTo("No additional observations");
        verify(springDataRepository).save(any(MentalStatusExamJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        MentalStatusExam exam = newMentalStatusExam();

        adapter.delete(exam);

        verify(springDataRepository).deleteById(exam.id().value());
    }

    private MentalStatusExam newMentalStatusExam() {
        return MentalStatusExam.register(
                new EncounterId(UUID.randomUUID()),
                "Neat",
                "Cooperative",
                "Open",
                "Alert",
                "Oriented",
                "Sustained",
                "Intact",
                "Clear",
                "Euthymic",
                "Congruent",
                "Logical",
                "No delusions",
                "No alterations",
                "Preserved",
                "Adequate",
                "Normal",
                "No additional observations"
        );
    }
}
