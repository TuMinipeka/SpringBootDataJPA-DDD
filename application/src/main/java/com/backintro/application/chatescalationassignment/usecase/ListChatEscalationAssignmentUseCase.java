package com.backintro.application.chatescalationassignment.usecase;

import java.util.List;

import com.backintro.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.backintro.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.backintro.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class ListChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository assignmentRepository;

    public ListChatEscalationAssignmentUseCase(
            ChatEscalationAssignmentRepository assignmentRepository
    ) {
        this.assignmentRepository = assignmentRepository;
    }

    public List<ChatEscalationAssignmentResponse> execute() {
        return assignmentRepository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    private ChatEscalationAssignmentResponse toResponse(
            ChatEscalationAssignment assignment
    ) {
        return new ChatEscalationAssignmentResponse(
                assignment.id().value(),
                assignment.escalationId().value(),
                assignment.professionalId().value()
        );
    }
}
