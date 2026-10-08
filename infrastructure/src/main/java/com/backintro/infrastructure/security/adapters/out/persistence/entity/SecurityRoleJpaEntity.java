package com.backintro.infrastructure.security.adapters.out.persistence.entity;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "security_roles")
public class SecurityRoleJpaEntity {

    @Id
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;

    @Column(name = "name", nullable = false, unique = true, length = 50)
    private String name;

    @Column(name = "authority", nullable = false, unique = true, length = 100)
    private String authority;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected SecurityRoleJpaEntity() {
        // Required by JPA.
    }

    public SecurityRoleJpaEntity(UUID id, String name, String authority) {
        this.id = id;
        this.name = name;
        this.authority = authority;
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

    public String getName() {
        return name;
    }

    public String getAuthority() {
        return authority;
    }
}
