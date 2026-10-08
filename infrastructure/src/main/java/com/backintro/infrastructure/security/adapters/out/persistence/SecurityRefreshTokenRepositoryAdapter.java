package com.backintro.infrastructure.security.adapters.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.security.model.RefreshToken;
import com.backintro.domain.security.port.RefreshTokenRepository;
import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityRefreshTokenJpaEntity;
import com.backintro.infrastructure.security.adapters.out.persistence.mapper.SecurityRefreshTokenPersistenceMapper;
import com.backintro.infrastructure.security.adapters.out.persistence.repository.SecurityRefreshTokenJpaRepository;

@Repository
@Transactional
public class SecurityRefreshTokenRepositoryAdapter implements RefreshTokenRepository {

    private final SecurityRefreshTokenJpaRepository repository;
    private final SecurityRefreshTokenPersistenceMapper mapper;

    public SecurityRefreshTokenRepositoryAdapter(
            SecurityRefreshTokenJpaRepository repository,
            SecurityRefreshTokenPersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public RefreshToken save(RefreshToken refreshToken) {
        SecurityRefreshTokenJpaEntity entity = repository.findById(refreshToken.id())
                .map(existing -> {
                    mapper.synchronize(refreshToken, existing);
                    return existing;
                })
                .orElseGet(() -> mapper.toNewEntity(refreshToken));

        return mapper.toDomain(repository.save(entity));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RefreshToken> findByToken(String token) {
        return repository.findByToken(token).map(mapper::toDomain);
    }

    @Override
    public void deleteByUserId(UUID userId) {
        repository.deleteAllByUserId(userId);
    }
}
