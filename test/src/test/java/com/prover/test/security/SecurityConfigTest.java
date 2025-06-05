package com.prover.test.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

class SecurityConfigTest {

    @Test
    void shouldBuildSecurityFilterChain() throws Exception {
        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);
        AuthTokenFilter tokenFilter = mock(AuthTokenFilter.class);

        SecurityConfig config = new SecurityConfig();
        SecurityFilterChain chain = config.securityFilterChain(http, tokenFilter);

        assertNotNull(chain);
    }
}
