package com.backintro.domain.security.port;

import java.util.Optional;

import com.backintro.domain.security.model.Role;

public interface RoleRepository {

    Optional<Role> findByName(String name);

    Optional<Role> findByAuthority(String authority);
}
