package com.backintro.infrastructure.security.config;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import com.backintro.domain.security.port.TokenService;
import com.backintro.infrastructure.security.exception.SecurityAccessDeniedHandler;
import com.backintro.infrastructure.security.exception.SecurityAuthenticationEntryPoint;
import com.backintro.infrastructure.security.filter.JwtAuthenticationFilter;

@WebMvcTest(
        controllers = SecurityPolicyTestController.class,
        excludeAutoConfiguration = UserDetailsServiceAutoConfiguration.class
)
@Import({
        SecurityConfig.class,
        JwtAuthenticationFilter.class,
        SecurityAuthenticationEntryPoint.class,
        SecurityAccessDeniedHandler.class
})
class SecurityPolicyTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TokenService tokenService;

    @Test
    void permitsPublicAuthenticationEndpoints() throws Exception {
        mockMvc.perform(post("/api/auth/login"))
                .andExpect(status().isOk());
    }

    @Test
    void rejectsAnonymousRequestsToBoundedContexts() throws Exception {
        mockMvc.perform(get("/api/countries"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void permitsModeratorRequestsToBoundedContexts() throws Exception {
        mockMvc.perform(get("/api/countries").with(user("moderator").roles("MODERATOR")))
                .andExpect(status().isOk());
    }

    @Test
    void rejectsBasicUserRequestsToBoundedContexts() throws Exception {
        mockMvc.perform(get("/api/countries").with(user("user").roles("USER")))
                .andExpect(status().isForbidden());
    }

    @Test
    void rejectsNonAdminRequestsToSecurityAdministration() throws Exception {
        mockMvc.perform(get("/api/security/users").with(user("user").roles("USER")))
                .andExpect(status().isForbidden());
    }

    @Test
    void permitsAdminRequestsToSecurityAdministration() throws Exception {
        mockMvc.perform(get("/api/security/users").with(user("admin").roles("ADMIN")))
                .andExpect(status().isOk());
    }
}
