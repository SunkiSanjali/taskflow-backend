package com.taskflow.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI taskFlowOpenAPI() {

        return new OpenAPI()
                .info(new Info()
                        .title("TaskFlow API")
                        .version("1.0.0")
                        .description(
                                "TaskFlow is a task management backend built with Spring Boot. " +
                                        "It provides APIs for user authentication, task creation, " +
                                        "task updates, filtering, pagination, and security."
                        )
                        .contact(new Contact()
                                .name("Sanjali Reddy")
                                .email("sanjali@example.com")));
    }
}