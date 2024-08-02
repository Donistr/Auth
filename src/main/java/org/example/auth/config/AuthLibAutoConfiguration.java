package org.example.auth.config;

import org.example.auth.properties.AuthLibProperties;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableConfigurationProperties({AuthLibProperties.class})
@ComponentScan(basePackages = {"org.example.auth.config", "org.example.auth.controller", "org.example.auth.filter",
        "org.example.auth.jwt", "org.example.auth.service"})
@EnableJpaRepositories(basePackages = "org.example.auth.repository")
@EntityScan(basePackages = "org.example.auth.entity")
public class AuthLibAutoConfiguration {
}
