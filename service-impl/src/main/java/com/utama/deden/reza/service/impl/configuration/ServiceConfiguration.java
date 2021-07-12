package com.utama.deden.reza.service.impl.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@ComponentScan(basePackages = "com.utama.deden.reza.dao.*")
@ComponentScan(basePackages = "com.utama.deden.reza.service")
@EnableR2dbcRepositories(value = "com.utama.deden.reza.dao.*")
public class ServiceConfiguration {


}
