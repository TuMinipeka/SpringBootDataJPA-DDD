package com.backintro.infrastructure.chatparticipant.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_participants")
public class ChatParticipantJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "conversation_id", nullable = false, updatable = false)
    private UUID conversationId;

    @Column(name = "participant_type_id", nullable = false)
    private UUID participantTypeId;

    @Column(name = "patient_id")
    private UUID patientId;

    @Column(name = "professional_id")
    private UUID professionalId;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected ChatParticipantJpaEntity() {
        // Required by JPA.
    }

    public ChatParticipantJpaEntity(
            UUID id,
            UUID conversationId,
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId
    ) {
        this.id = id;
        this.conversationId = conversationId;
        this.participantTypeId = participantTypeId;
        this.patientId = patientId;
        this.professionalId = professionalId;
    }

    public void synchronize(
            UUID participantTypeId,
            UUID patientId,
            UUID professionalId
    ) {
        this.participantTypeId = participantTypeId;
        this.patientId = patientId;
        this.professionalId = professionalId;
    }

    @PrePersist
    void onCreate() {
        LocalDateTime now = LocalDateTime.now();
        if (createdAt == null) {
            createdAt = now;
        }
        updatedAt = now;
    }

    @PreUpdate
    void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public UUID getId() {
        return id;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public UUID getParticipantTypeId() {
        return participantTypeId;
    }

    public UUID getPatientId() {
        return patientId;
    }

    public UUID getProfessionalId() {
        return professionalId;
    }
}
