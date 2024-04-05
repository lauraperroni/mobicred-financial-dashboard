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
                        .requestMatchers(HttpMethod.GET, "/bankaccounts/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/bankaccounts/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/bankaccounts/new").hasRole("ADMIN")
                        // FinancialGoal endpoints
                        .requestMatchers(HttpMethod.GET, "/financialgoals/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/financialgoals/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/financialgoals/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/financialgoals/byPeriod/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/financialgoals/daysUntilDeadline/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/financialgoals/orderByAmount").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/financialgoals/orderByDeadline").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/financialgoals/orderByType").hasRole("ADMIN")
                        // Category endpoints
                        .requestMatchers(HttpMethod.GET, "/categories/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/categories/new").hasRole("ADMIN")
                        // Transaction endpoints
                        .requestMatchers(HttpMethod.GET, "/transactions/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/transactions/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/transactions/new").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/bankaccount/{bankAccountId}")
                        .hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/bankaccount/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/category/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/price").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/transactions/sort/date").hasRole("ADMIN")
                        // User endpoints
                        .requestMatchers(HttpMethod.GET, "/users/{id}").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.GET, "/users/all").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/users/new").hasRole("ADMIN")
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
