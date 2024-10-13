package com.bitside.challenge.shoppingbasket.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI shoppingBasketOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Shopping Basket API")
                        .description("API for managing a shopping basket")
                        .version("1.0"));
    }
}
