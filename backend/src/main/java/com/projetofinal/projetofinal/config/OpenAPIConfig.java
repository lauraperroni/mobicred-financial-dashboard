package com.projetofinal.projetofinal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;

@OpenAPIDefinition
@Configuration
public class OpenAPIConfig {

    @Bean
    public OpenAPI financialDashboardAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Financial Dashboard API")
                        .description("Financial Dashboard Project")
                        .version("v0.0.1")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/lauraperroni/financial-dashboard")));
    }
}