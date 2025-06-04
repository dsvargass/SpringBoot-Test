package com.prover.test.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.http.HttpMethod;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http, AuthTokenFilter authTokenFilter) throws Exception {
        return http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/v3/api-docs/**",
                                "/swagger-ui/**",
                                "/swagger-ui.html"
                        ).permitAll()
                        .requestMatchers(HttpMethod.POST, "/clients").permitAll()
                        .requestMatchers(HttpMethod.GET, "/clients").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/clients/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/clients/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/clients/upload").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(authTokenFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}
