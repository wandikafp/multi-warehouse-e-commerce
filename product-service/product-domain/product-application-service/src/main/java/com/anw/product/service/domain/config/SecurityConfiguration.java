package com.anw.product.service.domain.config;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Configuration
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final ProductServiceConfig productServiceConfig;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(customizer -> {
            customizer
                    .requestMatchers("/api/**").permitAll()
                    .anyRequest().authenticated();
        });

        http.csrf().disable(); // Disable CSRF protection

//       http.csrf(csrf -> {
//           csrf
//                   .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
//                   .csrfTokenRequestHandler(new SpaCsrfTokenRequestHandler());
//       }).addFilterAfter(new CsrfCookieFilter(), BasicAuthenticationFilter.class);

        http.cors(customizer -> {
            customizer.configurationSource(corsConfigurationSource());
        });

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration config = new CorsConfiguration();
                config.setAllowedOrigins(productServiceConfig.getAllowedOrigins());
                config.setAllowedMethods(List.of("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
                config.setAllowedHeaders(List.of("*"));
                config.setAllowCredentials(true);
                return config;
            }
        };
    }
}
