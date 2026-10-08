package com.backintro.infrastructure.security.adapters.out.persistence;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.security.model.Role;
import com.backintro.domain.security.model.SecurityUser;
import com.backintro.domain.security.port.SecurityUserRepository;
import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityRoleJpaEntity;
import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityUserJpaEntity;
import com.backintro.infrastructure.security.adapters.out.persistence.entity.SecurityUserRoleJpaEntity;
import com.backintro.infrastructure.security.adapters.out.persistence.mapper.SecurityRolePersistenceMapper;
import com.backintro.infrastructure.security.adapters.out.persistence.mapper.SecurityUserPersistenceMapper;
import com.backintro.infrastructure.security.adapters.out.persistence.repository.SecurityRoleJpaRepository;
import com.backintro.infrastructure.security.adapters.out.persistence.repository.SecurityUserJpaRepository;
import com.backintro.infrastructure.security.adapters.out.persistence.repository.SecurityUserRoleJpaRepository;

@Repository
@Transactional
public class SecurityUserRepositoryAdapter implements SecurityUserRepository {

    private final SecurityUserJpaRepository userRepository;
    private final SecurityRoleJpaRepository roleRepository;
    private final SecurityUserRoleJpaRepository userRoleRepository;
    private final SecurityUserPersistenceMapper userMapper;
    private final SecurityRolePersistenceMapper roleMapper;

    public SecurityUserRepositoryAdapter(
            SecurityUserJpaRepository userRepository,
            SecurityRoleJpaRepository roleRepository,
            SecurityUserRoleJpaRepository userRoleRepository,
            SecurityUserPersistenceMapper userMapper,
            SecurityRolePersistenceMapper roleMapper
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userRoleRepository = userRoleRepository;
        this.userMapper = userMapper;
        this.roleMapper = roleMapper;
    }

    @Override
    public SecurityUser save(SecurityUser user) {
        SecurityUserJpaEntity entity = userRepository.findById(user.id())
                .map(existing -> {
                    userMapper.synchronize(user, existing);
                    return existing;
                })
                .orElseGet(() -> userMapper.toNewEntity(user));

        SecurityUserJpaEntity saved = userRepository.save(entity);
        userRoleRepository.deleteAllByUserId(saved.getId());
        userRoleRepository.saveAll(
                user.roles().stream()
                        .map(role -> new SecurityUserRoleJpaEntity(saved.getId(), role.id()))
                        .toList()
        );

        return userMapper.toDomain(saved, user.roles());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SecurityUser> findById(UUID id) {
        return userRepository.findById(id).map(this::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<SecurityUser> findByEmail(String email) {
        return userRepository.findByEmailIgnoreCase(email.trim().toLowerCase(Locale.ROOT))
                .map(this::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return userRepository.existsByEmailIgnoreCase(email);
    }

    private SecurityUser toDomain(SecurityUserJpaEntity entity) {
        List<UUID> roleIds = userRoleRepository.findAllByUserId(entity.getId()).stream()
                .map(SecurityUserRoleJpaEntity::getRoleId)
                .toList();
        Set<Role> roles = new LinkedHashSet<>();
        for (SecurityRoleJpaEntity roleEntity : roleRepository.findAllById(roleIds)) {
            roles.add(roleMapper.toDomain(roleEntity));
        }
        return userMapper.toDomain(entity, roles);
    }
}
