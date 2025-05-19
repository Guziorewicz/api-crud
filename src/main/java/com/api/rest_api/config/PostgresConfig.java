package com.api.rest_api.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.api.rest_api.repository")
@EntityScan(basePackages = "com.api.rest_api.model")
public class PostgresConfig {

}