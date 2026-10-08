package com.backintro.infrastructure.security.adapters.out.persistence;

import java.util.Optional;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.backintro.domain.security.model.Role;
import com.backintro.domain.security.port.RoleRepository;
import com.backintro.infrastructure.security.adapters.out.persistence.mapper.SecurityRolePersistenceMapper;
import com.backintro.infrastructure.security.adapters.out.persistence.repository.SecurityRoleJpaRepository;

@Repository
@Transactional(readOnly = true)
public class SecurityRoleRepositoryAdapter implements RoleRepository {

    private final SecurityRoleJpaRepository repository;
    private final SecurityRolePersistenceMapper mapper;

    public SecurityRoleRepositoryAdapter(
            SecurityRoleJpaRepository repository,
            SecurityRolePersistenceMapper mapper
    ) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<Role> findByName(String name) {
        return repository.findByNameIgnoreCase(name).map(mapper::toDomain);
    }

    @Override
    public Optional<Role> findByAuthority(String authority) {
        return repository.findByAuthorityIgnoreCase(authority).map(mapper::toDomain);
    }
}
