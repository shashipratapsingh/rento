package ownerService.config;

import lombok.RequiredArgsConstructor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())

                .sessionManagement(session ->
                        session.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )

                .authorizeHttpRequests(auth -> auth

                        // =========================
                        // OWNER
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/owners"
                        ).hasRole("OWNER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/owners/**"
                        ).hasRole("OWNER")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/owners/**"
                        ).hasRole("OWNER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/owners/**"
                        ).hasRole("OWNER")


                        // =========================
                        // ROOM - OWNER
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/rooms"
                        ).hasRole("OWNER")

                        .requestMatchers(
                                HttpMethod.PUT,
                                "/api/rooms/**"
                        ).hasRole("OWNER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/rooms/**"
                        ).hasRole("OWNER")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/rooms/**"
                        ).hasRole("OWNER")


                        // =========================
                        // ROOM - OWNER + CUSTOMER
                        // =========================

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/rooms/search/**"
                        ).hasAnyRole("OWNER", "CUSTOMER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/rooms/{id}"
                        ).hasAnyRole("OWNER", "CUSTOMER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/rooms/*/photos"
                        ).hasAnyRole("OWNER", "CUSTOMER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/rooms/owner/**"
                        ).hasRole("OWNER")


                        // =========================
                        // PHOTO - OWNER
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/rooms/*/photos"
                        ).hasRole("OWNER")

                        .requestMatchers(
                                HttpMethod.DELETE,
                                "/api/rooms/photos/**"
                        ).hasRole("OWNER")


                        // =========================
                        // REPORT
                        // =========================

                        .requestMatchers(
                                HttpMethod.POST,
                                "/api/reports/rooms/**"
                        ).hasRole("CUSTOMER")

                        .requestMatchers(
                                HttpMethod.GET,
                                "/api/reports/**"
                        ).hasRole("ADMIN")

                        .requestMatchers(
                                HttpMethod.PATCH,
                                "/api/reports/**"
                        ).hasRole("ADMIN")


                        // =========================
                        // VERIFICATION
                        // =========================

                        .requestMatchers(
                                "/api/verification/**"
                        ).hasRole("ADMIN")


                        // Everything else
                        .anyRequest().authenticated()
                )

                .addFilterBefore(
                        jwtAuthenticationFilter,
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }
}