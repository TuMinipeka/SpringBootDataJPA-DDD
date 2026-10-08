package com.backintro.domain.security.port;

import java.util.Optional;
import java.util.UUID;

import com.backintro.domain.security.model.SecurityUser;

public interface SecurityUserRepository {

    SecurityUser save(SecurityUser user);

    Optional<SecurityUser> findById(UUID id);

    Optional<SecurityUser> findByEmail(String email);

    boolean existsByEmail(String email);
}
