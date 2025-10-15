package com.demo.immobiliare.config;

import com.demo.immobiliare.model.Utente;
import com.demo.immobiliare.repository.UtenteRepository;
import com.demo.immobiliare.security.JwtTokenFilter;
import com.demo.immobiliare.security.JwtUtil;

import jakarta.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfiguration {

    private final UtenteRepository utenteRepository;
    private final JwtUtil jwtUtil;

    public SecurityConfiguration(UtenteRepository utenteRepository, JwtUtil jwtUtil) {
        this.utenteRepository = utenteRepository;
        this.jwtUtil = jwtUtil;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
    @Bean
    public JwtTokenFilter jwtTokenFilter(UserDetailsService userDetailsService) {
        return new JwtTokenFilter(jwtUtil, userDetailsService);
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
    public SecurityFilterChain filterChain(HttpSecurity http, JwtTokenFilter jwtTokenFilter) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .exceptionHandling(ex -> ex
            	    .authenticationEntryPoint((req, res, authEx) ->
            	      res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Non autenticato"))
            	    .accessDeniedHandler((req, res, accessEx) ->
            	      res.sendError(HttpServletResponse.SC_FORBIDDEN, "Accesso negato"))
            	  )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.POST, "/utenti/register").permitAll()
                .requestMatchers(HttpMethod.POST, "/utenti/login").permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(jwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}

