package com.backintro.infrastructure.security.adapters.out.persistence.entity;

import java.io.Serializable;
import java.util.Objects;
import java.util.UUID;

public class SecurityUserRoleId implements Serializable {

    private UUID userId;
    private UUID roleId;

    public SecurityUserRoleId() {
        // Required by JPA.
    }

    public SecurityUserRoleId(UUID userId, UUID roleId) {
        this.userId = userId;
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object candidate) {
        if (this == candidate) {
            return true;
        }
        if (!(candidate instanceof SecurityUserRoleId that)) {
            return false;
        }
        return Objects.equals(userId, that.userId)
                && Objects.equals(roleId, that.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, roleId);
    }
}
