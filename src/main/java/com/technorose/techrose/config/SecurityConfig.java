package com.technorose.techrose.config;

import com.technorose.techrose.filters.JwtAuthenticationFilter;
import com.technorose.techrose.service.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@Configuration
public class SecurityConfig {

    private String[] publicEndpoints = {
            "user/user-register",
            "user/user-login",
            "/swagger-ui/**",
            "/v3/api-docs/**"
    };

    private final UserDetailsServiceImpl userDetailsService;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.userDetailsService = userDetailsService;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        req -> req.requestMatchers(publicEndpoints)
                                .permitAll()
                                .requestMatchers("/admin/**")
                                .hasAuthority("ADMIN")
                                .anyRequest()
                                .authenticated()
                )
                .userDetailsService(userDetailsService)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(
                        ex -> ex.accessDeniedHandler(
                                ((request, response, accessDeniedException) -> response.setStatus(403))
                        )
                                .authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
                )
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
