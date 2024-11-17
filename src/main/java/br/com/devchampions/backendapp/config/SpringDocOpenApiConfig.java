package br.com.devchampions.backendapp.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {

    private static final String BEARER_AUTHENTICATION = "Bearer Authentication";
    private static final String BEARER_FORMAT = "JWT";
    private static final String BEARER_SCHEMA = "bearer";

    @Bean
    public OpenAPI openAPI() {

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(BEARER_AUTHENTICATION))
                .components(new Components().addSecuritySchemes(BEARER_AUTHENTICATION, createAPIKeyScheme()))
                .info(info());

    }

    private SecurityScheme createAPIKeyScheme() {
        return new SecurityScheme().type(SecurityScheme.Type.HTTP)
                .bearerFormat(BEARER_FORMAT)
                .scheme(BEARER_SCHEMA);
    }

    private Info info() {
        return new Info().title("APP Core Backend")
                .description("APP Core Backend")
                .version("v1.0.0")
                .license(license())
                .contact(contact());
    }

    private Contact contact() {
        return new Contact().name("Dev Champions").email("suporte@devchampions.com.br");
    }

    private License license() {
        return new License().name("Apache 2.0")
                .url("https://www.apache.org/licenses/LICENSE-2.0");
    }

}
