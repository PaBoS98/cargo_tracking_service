package com.pavlob.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Arrays;

@Configuration
public class SwaggerConfig {

    @Autowired
    BuildProperties buildProperties;

    @Autowired
    Environment env;

    @Bean
    public OpenAPI springServiceOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("Public API")
                .packagesToScan("com.pavlob.cargotracking")
                .build();
    }

    private Info apiInfo() {
        return new Info()
                .title(String.format("%s (%s %s)",
                                buildProperties.getName(),
                                " ",
                                Arrays.toString(env.getActiveProfiles())))
                .version(buildProperties.getVersion())
                .contact(
                        new Contact()
                                .name("Andersen-Team")
                                .email("p.bohomaz@andersenlab.com"));
    }
}
