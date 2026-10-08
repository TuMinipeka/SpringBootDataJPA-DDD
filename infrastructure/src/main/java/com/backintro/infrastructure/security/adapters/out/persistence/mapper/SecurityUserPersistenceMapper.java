package com.backintro.infrastructure.security.adapters.out.persistence.mapper;

import java.util.Set;

import org.springframework.stereotype.Component;

import com.backintro.domain.security.model.Role;
import com.backintro.domain.security.model.SecurityUser;
import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityUserJpaEntity;

@Component
public class SecurityUserPersistenceMapper {

    public SecurityUserJpaEntity toNewEntity(SecurityUser user) {
        return new SecurityUserJpaEntity(
                user.id(),
                user.email(),
                user.passwordHash(),
                user.status()
        );
    }

    public void synchronize(SecurityUser user, SecurityUserJpaEntity entity) {
        entity.synchronize(
                user.email(),
                user.passwordHash(),
                user.status()
        );
    }

    public SecurityUser toDomain(SecurityUserJpaEntity entity, Set<Role> roles) {
        return SecurityUser.restore(
                entity.getId(),
                entity.getEmail(),
                entity.getPasswordHash(),
                roles,
                entity.getStatus()
        );
    }
}
