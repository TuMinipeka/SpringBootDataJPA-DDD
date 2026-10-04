package com.backintro.infrastructure.chatconversation.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "chat_conversations")
public class ChatConversationJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "conversation_status_id", nullable = false)
    private UUID conversationStatusId;

    @Column(name = "priority_id")
    private UUID priorityId;

    @Column(name = "last_message_at")
    private LocalDateTime lastMessageAt;

    @Column(name = "closed", nullable = false)
    private boolean closed;

    @Column(name = "closed_at")
    private LocalDateTime closedAt;

    @Column(name = "closed_by")
    private UUID closedBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    protected ChatConversationJpaEntity() {
        // Required by JPA.
    }

    public ChatConversationJpaEntity(
            UUID id,
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            boolean closed,
            LocalDateTime closedAt,
            UUID closedBy
    ) {
        this.id = id;
        this.conversationStatusId = conversationStatusId;
        this.priorityId = priorityId;
        this.lastMessageAt = lastMessageAt;
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
    }

    public void synchronize(
            UUID conversationStatusId,
            UUID priorityId,
            LocalDateTime lastMessageAt,
            boolean closed,
            LocalDateTime closedAt,
            UUID closedBy
    ) {
        this.conversationStatusId = conversationStatusId;
        this.priorityId = priorityId;
        this.lastMessageAt = lastMessageAt;
        this.closed = closed;
        this.closedAt = closedAt;
        this.closedBy = closedBy;
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

    public UUID getConversationStatusId() {
        return conversationStatusId;
    }

    public UUID getPriorityId() {
        return priorityId;
    }

    public LocalDateTime getLastMessageAt() {
        return lastMessageAt;
    }

    public boolean isClosed() {
        return closed;
    }

    public LocalDateTime getClosedAt() {
        return closedAt;
    }

    public UUID getClosedBy() {
        return closedBy;
    }
}
