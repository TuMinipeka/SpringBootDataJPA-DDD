package com.backintro.application.chatescalationassignment.usecase;

import com.backintro.application.chatescalationassignment.command.UpdateChatEscalationAssignmentCommand;
import com.backintro.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.backintro.application.chatescalationassignment.exception.ChatEscalationAssignmentNotFoundApplicationException;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.backintro.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class UpdateChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository assignmentRepository;
    private final ProfessionalRepository professionalRepository;

    public UpdateChatEscalationAssignmentUseCase(
            ChatEscalationAssignmentRepository assignmentRepository,
            ProfessionalRepository professionalRepository
    ) {
        this.assignmentRepository = assignmentRepository;
        this.professionalRepository = professionalRepository;
    }

    public ChatEscalationAssignmentResponse execute(
            UpdateChatEscalationAssignmentCommand command
    ) {
        var assignment = assignmentRepository.findById(command.id())
                .orElseThrow(() ->
                        new ChatEscalationAssignmentNotFoundApplicationException(
                                command.id().value().toString()
                        )
                );
        validateProfessional(command.professionalId());

        assignment.update(command.professionalId());

        return toResponse(assignmentRepository.save(assignment));
    }

    private void validateProfessional(ProfessionalId professionalId) {
        professionalRepository.findById(professionalId)
                .orElseThrow(() ->
                        new ProfessionalNotFoundApplicationException(
                                professionalId.value().toString()
                        )
                );
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
