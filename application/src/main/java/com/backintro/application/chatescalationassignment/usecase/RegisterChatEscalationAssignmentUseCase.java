package com.backintro.application.chatescalationassignment.usecase;

import com.backintro.application.chatescalation.exception.ChatEscalationNotFoundApplicationException;
import com.backintro.application.chatescalationassignment.command.RegisterChatEscalationAssignmentCommand;
import com.backintro.application.chatescalationassignment.dto.ChatEscalationAssignmentResponse;
import com.backintro.application.professional.exception.ProfessionalNotFoundApplicationException;
import com.backintro.domain.chatescalation.port.repository.ChatEscalationRepository;
import com.backintro.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.backintro.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.domain.professional.port.repository.ProfessionalRepository;

public class RegisterChatEscalationAssignmentUseCase {

    private final ChatEscalationAssignmentRepository assignmentRepository;
    private final ChatEscalationRepository escalationRepository;
    private final ProfessionalRepository professionalRepository;

    public RegisterChatEscalationAssignmentUseCase(
            ChatEscalationAssignmentRepository assignmentRepository,
            ChatEscalationRepository escalationRepository,
            ProfessionalRepository professionalRepository
    ) {
        this.assignmentRepository = assignmentRepository;
        this.escalationRepository = escalationRepository;
        this.professionalRepository = professionalRepository;
    }

    public ChatEscalationAssignmentResponse execute(
            RegisterChatEscalationAssignmentCommand command
    ) {
        escalationRepository.findById(command.escalationId())
                .orElseThrow(() ->
                        new ChatEscalationNotFoundApplicationException(
                                command.escalationId().value().toString()
                        )
                );
        validateProfessional(command.professionalId());

        ChatEscalationAssignment assignment =
                ChatEscalationAssignment.register(
                        command.escalationId(),
                        command.professionalId()
                );

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
