package com.backintro.infrastructure.study.adapters.out.persistence;

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

import com.backintro.domain.study.model.aggregate.Study;
import com.backintro.infrastructure.study.adapters.out.persistence.entity.StudyJpaEntity;
import com.backintro.infrastructure.study.adapters.out.persistence.mapper.StudyPersistenceMapper;
import com.backintro.infrastructure.study.adapters.out.persistence.repository.StudyJpaRepository;

@ExtendWith(MockitoExtension.class)
class StudyRepositoryAdapterTest {

    @Mock
    private StudyJpaRepository springDataRepository;

    private StudyRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new StudyRepositoryAdapter(
                springDataRepository,
                new StudyPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        Study study = Study.register("Psychology");
        when(springDataRepository.findById(study.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(any(StudyJpaEntity.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Study saved = adapter.save(study);

        assertThat(saved.id()).isEqualTo(study.id());
        assertThat(saved.name()).isEqualTo("Psychology");
        verify(springDataRepository).save(any(StudyJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        Study study = Study.register("Psychology");

        adapter.delete(study);

        verify(springDataRepository).deleteById(study.id().value());
    }
}
