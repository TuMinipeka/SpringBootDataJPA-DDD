package com.backintro.infrastructure.security.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.security.model.Role;
import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityRoleJpaEntity;

@Component
public class SecurityRolePersistenceMapper {

    public Role toDomain(SecurityRoleJpaEntity entity) {
        return Role.restore(
                entity.getId(),
                entity.getName(),
                entity.getAuthority()
        );
    }
}
