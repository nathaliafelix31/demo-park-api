package com.felix.demoparkapi.configs;

import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    @Bean
    public OpenAPI openApi(){
        return new OpenAPI()
                .info(
                        new Info()
                        .title("REST API - Spring Park")
                        .descrption("API para gestão de estacionamento")
                        .version("v1")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                        .contact(new Contact().name("Nathália Félix").email("nathy.felix.31@gmail.com"))
                );
    }

}
