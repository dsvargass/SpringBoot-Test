package com.prover.test.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.mockito.Mockito.*;

class AuthTokenFilterTest {

    private AuthTokenFilter filter;
    private HttpServletRequest request;
    private HttpServletResponse response;
    private FilterChain chain;

    @BeforeEach
    void setUp() {
        filter = new AuthTokenFilter();
        request = mock(HttpServletRequest.class);
        response = mock(HttpServletResponse.class);
        chain = mock(FilterChain.class);
    }

    @Test
    void shouldTriggerDoFilter() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/clients");
        when(request.getHeader("Authorization")).thenReturn("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJyb2xlIjoiYWRtaW4iLCJpYXQiOjE3NDkwNjUwMTJ9.VvRXcKdXN3pMPiQ8QKzGzl-VWnqdz_4uEFQZFlgFO-Q");

        filter.doFilter(request, response, chain);

        verify(chain).doFilter(request, response);
    }

    @Test
    void shouldReturn401WhenAuthorizationHeaderIsMissing() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/clients");
        when(request.getHeader("Authorization")).thenReturn(null);

        filter.doFilterInternal(request, response, chain);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Missing or invalid Authorization header");
        verifyNoInteractions(chain);
    }

    @Test
    void shouldReturn401WhenTokenIsInvalid() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/clients");
        when(request.getHeader("Authorization")).thenReturn("Bearer invalid_token");

        filter.doFilterInternal(request, response, chain);

        verify(response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Invalid token");
        verifyNoInteractions(chain);
    }

    @Test
    void shouldContinueFilterChainWhenTokenIsValid() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/clients");
        when(request.getHeader("Authorization")).thenReturn("Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpZCI6IjEiLCJyb2xlIjoiYWRtaW4iLCJpYXQiOjE3NDkwNjUwMTJ9.VvRXcKdXN3pMPiQ8QKzGzl-VWnqdz_4uEFQZFlgFO-Q");

        filter.doFilterInternal(request, response, chain);

        verify(chain).doFilter(request, response);
        verify(response, never()).sendError(anyInt(), anyString());
    }

    @Test
    void shouldNotFilterSwaggerPaths() throws ServletException, IOException {
        when(request.getRequestURI()).thenReturn("/swagger-ui/index.html");

        boolean result = filter.shouldNotFilter(request);

        assert result;
    }
}
