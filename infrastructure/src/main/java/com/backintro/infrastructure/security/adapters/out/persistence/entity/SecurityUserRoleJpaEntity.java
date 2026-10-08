package com.backintro.infrastructure.security.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@IdClass(SecurityUserRoleId.class)
@Table(name = "security_user_roles")
public class SecurityUserRoleJpaEntity {

    @Id
    @Column(name = "user_id", nullable = false, updatable = false)
    private UUID userId;

    @Id
    @Column(name = "role_id", nullable = false, updatable = false)
    private UUID roleId;

    @Column(name = "assigned_at", nullable = false, updatable = false)
    private LocalDateTime assignedAt;

    protected SecurityUserRoleJpaEntity() {
        // Required by JPA.
    }

    public SecurityUserRoleJpaEntity(UUID userId, UUID roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @PrePersist
    void onCreate() {
        if (assignedAt == null) {
            assignedAt = LocalDateTime.now();
        }
    }

    public UUID getUserId() {
        return userId;
    }

    public UUID getRoleId() {
        return roleId;
    }
}
