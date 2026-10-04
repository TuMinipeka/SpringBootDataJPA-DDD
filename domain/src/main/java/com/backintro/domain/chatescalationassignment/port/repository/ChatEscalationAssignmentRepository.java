package com.backintro.domain.chatescalationassignment.port.repository;

import java.util.List;
import java.util.Optional;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;

public interface ChatEscalationAssignmentRepository {

    ChatEscalationAssignment save(ChatEscalationAssignment assignment);

    Optional<ChatEscalationAssignment> findById(
            ChatEscalationAssignmentId id
    );

    List<ChatEscalationAssignment> findAll();

    boolean existsByEscalationIdAndProfessionalId(
            ChatEscalationId escalationId,
            ProfessionalId professionalId
    );

    void delete(ChatEscalationAssignment assignment);
}
