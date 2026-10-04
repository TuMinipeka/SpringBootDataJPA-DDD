package com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence;

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

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;
import com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.mapper.ChatEscalationAssignmentPersistenceMapper;
import com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.repository.ChatEscalationAssignmentJpaRepository;

@ExtendWith(MockitoExtension.class)
class ChatEscalationAssignmentRepositoryAdapterTest {

    @Mock
    private ChatEscalationAssignmentJpaRepository springDataRepository;

    private ChatEscalationAssignmentRepositoryAdapter adapter;

    @BeforeEach
    void setUp() {
        adapter = new ChatEscalationAssignmentRepositoryAdapter(
                springDataRepository,
                new ChatEscalationAssignmentPersistenceMapper()
        );
    }

    @Test
    void savesANewAggregateAndReturnsTheRestoredAggregate() {
        ChatEscalationAssignment assignment = newAssignment();
        when(springDataRepository.findById(assignment.id().value()))
                .thenReturn(Optional.empty());
        when(springDataRepository.save(
                any(ChatEscalationAssignmentJpaEntity.class)
        )).thenAnswer(invocation -> invocation.getArgument(0));

        ChatEscalationAssignment saved = adapter.save(assignment);

        assertThat(saved.id()).isEqualTo(assignment.id());
        assertThat(saved.escalationId())
                .isEqualTo(assignment.escalationId());
        assertThat(saved.professionalId())
                .isEqualTo(assignment.professionalId());
        verify(springDataRepository)
                .save(any(ChatEscalationAssignmentJpaEntity.class));
    }

    @Test
    void deletesUsingTheAggregateIdentifier() {
        ChatEscalationAssignment assignment = newAssignment();

        adapter.delete(assignment);

        verify(springDataRepository).deleteById(assignment.id().value());
    }

    private ChatEscalationAssignment newAssignment() {
        return ChatEscalationAssignment.register(
                ChatEscalationId.generate(),
                ProfessionalId.generate()
        );
    }
}
