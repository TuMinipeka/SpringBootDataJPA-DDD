package com.backintro.application.chatescalationassignment.usecase;

import java.time.LocalDateTime;

import com.backintro.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import com.backintro.domain.chatescalationassignment.event.ChatEscalationAssignmentDeletedEvent;
import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.backintro.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;

public class DeleteChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository assignmentRepository;

    public DeleteChatEscalationAssignmentUseCase(
            ChatEscalationAssignmentRepository assignmentRepository
    ) {
        this.assignmentRepository = assignmentRepository;
    }

    public ChatEscalationAssignmentDeletedEvent execute(
            ChatEscalationAssignmentId id
    ) {
        var assignment = assignmentRepository.findById(id)
                .orElseThrow(() ->
                        new ChatEscalationAssignmentNotFoundApplicationException(
                                id.value().toString()
                        )
                );

        assignmentRepository.delete(assignment);

        return new ChatEscalationAssignmentDeletedEvent(
                id,
                LocalDateTime.now()
        );
    }
}
