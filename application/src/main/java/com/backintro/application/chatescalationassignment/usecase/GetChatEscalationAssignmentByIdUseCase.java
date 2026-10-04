package com.backintro.application.chatescalationassignment.usecase;

import com.backintro.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.backintro.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import com.backintro.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.backintro.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class GetChatEscalationAssignmentByIdUseCase {

    private final ChatEscalationAssignmentRepository assignmentRepository;

    public GetChatEscalationAssignmentByIdUseCase(
            ChatEscalationAssignmentRepository assignmentRepository
    ) {
        this.assignmentRepository = assignmentRepository;
    }

    public ChatEscalationAssignmentResponse execute(
            ChatEscalationAssignmentId id
    ) {
        var assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ChatEscalationAssignmentNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        return toResponse(assignment);
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
