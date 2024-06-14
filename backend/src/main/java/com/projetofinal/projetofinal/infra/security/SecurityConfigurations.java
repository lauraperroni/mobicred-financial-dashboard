package com.projetofinal.projetofinal.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfigurations {

    @Autowired
    SecurityFilter securityFilter;

    // A corrente de filtros (métodos) para fazer validações
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        // Auth endpoints
                        .requestMatchers(HttpMethod.POST, "/auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST, "/auth/register").permitAll()
                        // BankAccount endpoints
                        .requestMatchers(HttpMethod.GET, "/bankaccounts/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/bankaccounts/update/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/bankaccounts/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/bankaccounts/user/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/bankaccounts/new").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/bankaccounts/delete/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET, "/financialgoals/user/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/financialgoals/{id}").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/financialgoals/update/{id}").permitAll()

                        .requestMatchers(HttpMethod.GET, "/financialgoals/name/desc").permitAll()
                        .requestMatchers(HttpMethod.GET, "/financialgoals/sort/amount/asc").permitAll()
                        .requestMatchers(HttpMethod.GET, "/financialgoals/sort/amount/desc").permitAll()
                        .requestMatchers(HttpMethod.GET, "/financialgoals/sort/deadline/asc").permitAll()
                        .requestMatchers(HttpMethod.GET, "/financialgoals/sort/deadline/desc").permitAll()
                        .requestMatchers(HttpMethod.GET, "/financialgoals/search/name/{name}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/financialgoals/search/name/{name}").permitAll()
                        // Category endpoints
                        .requestMatchers(HttpMethod.GET, "/categories/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categories/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/categories/new").permitAll()
                        // Transaction endpoints
                        .requestMatchers(HttpMethod.GET, "/transactions/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/transactions/update/{id}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/transactions/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/transactions/user/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/transactions/new").permitAll()
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/bankaccount/")
                        .permitAll()
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/bankaccount/all").permitAll()
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/category/{categoryId}").permitAll()
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/price").permitAll()
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/date").permitAll()
                        // User endpoints
                        .requestMatchers(HttpMethod.GET, "/users/{id}").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/users/update").permitAll()

                        .requestMatchers(HttpMethod.PUT, "/users/reset").permitAll() // apenas em caso de emergencia
 
                        .requestMatchers(HttpMethod.PUT, "/users/update/password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/change-password").permitAll()
                        .requestMatchers(HttpMethod.GET, "/users/all").permitAll()
                        .requestMatchers(HttpMethod.POST, "/users/new").permitAll()
                        // Swagger endpoints
                        .requestMatchers("/swagger-ui/**", "/docs", "/api-docs/**").permitAll()
                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    // Criptografa os dados de login e senha
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
