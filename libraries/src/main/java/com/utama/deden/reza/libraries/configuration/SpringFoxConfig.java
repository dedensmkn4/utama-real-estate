package com.utama.deden.reza.libraries.configuration;

import io.swagger.annotations.ApiOperation;
import java.util.Arrays;
import java.util.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SpringFoxConfig {
    private static final String STRING = "string";
    private static final String HEADER = "header";
    private static final String USERNAME = "username";

    @Bean
    public Docket init() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo())
                .globalOperationParameters(Arrays.asList(new ParameterBuilder().name("lang").parameterType
                                (HEADER)
                                .modelRef(new ModelRef(STRING)).required(true).defaultValue("ID")
                                .description("client's language").build(),
                        new ParameterBuilder().name("storeId").parameterType(HEADER)
                                .modelRef(new ModelRef(STRING)).required(true).defaultValue("UTAMA")
                                .description("client's store id").build(),
                        new ParameterBuilder().name("channelId").parameterType(HEADER)
                                .modelRef(new ModelRef(STRING)).required(true).defaultValue("WEB")
                                .description("client's channel id").build(),
                        new ParameterBuilder().name("requestId").parameterType(HEADER)
                                .modelRef(new ModelRef(STRING)).required(true).defaultValue("1")
                                .description("client's request Id").build(),
                        new ParameterBuilder().name("serviceId").parameterType(HEADER)
                                .modelRef(new ModelRef(STRING)).required(true).defaultValue("1")
                                .description("client's serviceId").build(),
                        new ParameterBuilder().name(USERNAME).parameterType(HEADER)
                                .modelRef(new ModelRef(STRING)).required(true).defaultValue(USERNAME)
                                .description("username").build()));
    }

    private ApiInfo apiInfo() {
        return new ApiInfo(
                "Utama Real Estate REST API",
                "Utama Real Estate REST API.",
                "API v2",
                "Terms of service",
                new Contact("UtamaRealEstate", "http://localhost:8092/", "deden.farhan@widyatama.ac.id"),
                "License of API", "API license URL", Collections.emptyList());
    }
}
