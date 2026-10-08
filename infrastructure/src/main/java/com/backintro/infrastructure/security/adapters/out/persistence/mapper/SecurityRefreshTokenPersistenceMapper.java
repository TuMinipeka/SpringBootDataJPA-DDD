package com.backintro.infrastructure.security.adapters.out.persistence.mapper;

import org.springframework.stereotype.Component;

import com.backintro.domain.security.model.RefreshToken;
import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityRefreshTokenJpaEntity;

@Component
public class SecurityRefreshTokenPersistenceMapper {

    public SecurityRefreshTokenJpaEntity toNewEntity(RefreshToken refreshToken) {
        return new SecurityRefreshTokenJpaEntity(
                refreshToken.id(),
                refreshToken.userId(),
                refreshToken.token(),
                refreshToken.expiresAt(),
                refreshToken.revoked()
        );
    }

    public void synchronize(RefreshToken refreshToken, SecurityRefreshTokenJpaEntity entity) {
        entity.synchronize(refreshToken.revoked());
    }

    public RefreshToken toDomain(SecurityRefreshTokenJpaEntity entity) {
        return RefreshToken.restore(
                entity.getId(),
                entity.getUserId(),
                entity.getToken(),
                entity.getExpiresAt(),
                entity.isRevoked()
        );
    }
}
