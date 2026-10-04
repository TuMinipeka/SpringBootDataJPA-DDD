package com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.chatescalation.model.valueobject.ChatEscalationId;
import com.backintro.domain.chatescalationassignment.model.aggregate.ChatEscalationAssignment;
import com.backintro.domain.chatescalationassignment.model.valueobject.ChatEscalationAssignmentId;
import com.backintro.domain.chatescalationassignment.port.repository.ChatEscalationAssignmentRepository;
import com.backintro.domain.professional.model.valueobject.ProfessionalId;
import com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.entity.ChatEscalationAssignmentJpaEntity;
import com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.mapper.ChatEscalationAssignmentPersistenceMapper;
import com.backintro.infrastructure.chatescalationassignment.adapters.out.persistence.repository.ChatEscalationAssignmentJpaRepository;

@Repository
@Transactional
public class ChatEscalationAssignmentRepositoryAdapter
        implements ChatEscalationAssignmentRepository {

    private final ChatEscalationAssignmentJpaRepository repository;
    private final ChatEscalationAssignmentPersistenceMapper mapper;

    public ChatEscalationAssignmentRepositoryAdapter(
            ChatEscalationAssignmentJpaRepository repository,
            ChatEscalationAssignmentPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public ChatEscalationAssignment save(
            ChatEscalationAssignment assignment
    ) {
        ChatEscalationAssignmentJpaEntity entity = repository
                .findById(assignment.id().value())
                .map(existing -> {
                    mapper.synchronize(assignment, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(assignment));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ChatEscalationAssignment> findById(
            ChatEscalationAssignmentId id
    ) {
        return repository.findById(id.value()).map(mapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ChatEscalationAssignment> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEscalationIdAndProfessionalId(
            ChatEscalationId escalationId,
            ProfessionalId professionalId
    ) {
        return repository.existsByEscalationIdAndProfessionalId(
                escalationId.value(),
                professionalId.value()
        );
    }

    @Override
    public void delete(ChatEscalationAssignment assignment) {
        repository.deleteById(assignment.id().value());
    }
}
