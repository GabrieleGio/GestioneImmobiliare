package com.demo.immobiliare.config;

import com.demo.immobiliare.model.Utente;
import com.demo.immobiliare.repository.UtenteRepository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    private final UtenteRepository utenteRepository;

    public SecurityConfiguration(UtenteRepository utenteRepository) {
        this.utenteRepository = utenteRepository;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return email -> {
            Utente utente = utenteRepository.findByEmail(email)
                    .orElseThrow(() -> new UsernameNotFoundException("Utente non trovato con email: " + email));

            return User.builder()
                    .username(utente.getEmail()) // come username uso l'email
                    .password(utente.getPassword())
                    .roles(utente.getRuolo().name())
                    .build();
        };
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // questo serve per evitare che il cookie si ricordi la sessione
            .authorizeHttpRequests(auth -> auth
            	    .requestMatchers(HttpMethod.POST, "/utenti").permitAll()
            	    .requestMatchers(HttpMethod.POST, "/utenti/login").permitAll()
            	    .requestMatchers("/immobili/**", "/annunci/**", "/trattative/**").permitAll()
            	    .anyRequest().authenticated()
            	)

            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}

