package com.backintro.domain.security.port;

import com.backintro.domain.security.model.SecurityUser;
import com.backintro.domain.security.model.TokenPrincipal;

public interface TokenService {

    String generateAccessToken(SecurityUser user);

    String generateRefreshToken();

    TokenPrincipal parseAccessToken(String token);
}
