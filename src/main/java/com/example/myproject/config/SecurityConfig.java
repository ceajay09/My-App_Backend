package com.example.myproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // Verwendung der DSL für eine explizite Konfiguration
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/register","/blog","/aboutMe","/",
                                "/aboutThisWebsite", "/techInsights", "/latestPosts", "/contactMe",
                                "/imprint", "/api/getBlogs", "/api/downloadPDF/**").permitAll() // Anpassung der Zugriffserlaubnis TODO: Zugriffe
                        .anyRequest().authenticated() // Anforderung der Authentifizierung für alle anderen Anfragen
                )
                .httpBasic(withDefaults()) // Einrichtung der Basisauthentifizierung, kann durch JWT-Authentifizierung ersetzt werden
                // Standardmäßige CSRF-Protektion ist für APIs, die von nicht browserbasierten Clients konsumiert werden, oft deaktiviert
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)); // Konfiguration für stateless Anwendungen

        return http.build();
    }

    // Definition des PasswordEncoder Bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}


