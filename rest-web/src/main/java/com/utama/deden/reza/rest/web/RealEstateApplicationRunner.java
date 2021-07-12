package com.utama.deden.reza.rest.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@SpringBootApplication
@ComponentScan(basePackages = {"com.utama.deden.reza.*"})
@EnableR2dbcRepositories
public class RealEstateApplicationRunner {
    public static void main(String[] args) {
        SpringApplication.run(RealEstateApplicationRunner.class, args);
    }



}
