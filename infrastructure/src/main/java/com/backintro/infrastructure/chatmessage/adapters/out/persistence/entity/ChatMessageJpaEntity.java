package com.backintro.infrastructure.chatmessage.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_messages")
public class ChatMessageJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "conversation_id", nullable = false, updatable = false)
    private UUID conversationId;

    @Column(name = "message_type_id", nullable = false)
    private UUID messageTypeId;

    @Column(name = "participant_id", nullable = false, updatable = false)
    private UUID participantId;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "content", nullable = false, columnDefinition = "jsonb")
    private String content;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "metadata", columnDefinition = "jsonb")
    private String metadata;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected ChatMessageJpaEntity() {
        // Required by JPA.
    }

    public ChatMessageJpaEntity(
            UUID id,
            UUID conversationId,
            UUID messageTypeId,
            UUID participantId,
            String content,
            String metadata
    ) {
        this.id = id;
        this.conversationId = conversationId;
        this.messageTypeId = messageTypeId;
        this.participantId = participantId;
        this.content = content;
        this.metadata = metadata;
    }

    public void synchronize(
            UUID messageTypeId,
            String content,
            String metadata
    ) {
        this.messageTypeId = messageTypeId;
        this.content = content;
        this.metadata = metadata;
    }

    @PrePersist
    void onCreate() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
    }

    public UUID getId() {
        return id;
    }

    public UUID getConversationId() {
        return conversationId;
    }

    public UUID getMessageTypeId() {
        return messageTypeId;
    }

    public UUID getParticipantId() {
        return participantId;
    }

    public String getContent() {
        return content;
    }

    public String getMetadata() {
        return metadata;
    }
}
