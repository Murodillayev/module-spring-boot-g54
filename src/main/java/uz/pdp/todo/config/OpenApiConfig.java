package uz.pdp.todo.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;


@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "CMS API",
                description = "API for Clinic management system",
                version = "1.1",
                contact = @Contact(
                        name = "Olmos Soft",
                        email = "olomossoft@gamil.com",
                        url = "https://olmos.soft.uz/api"
                )
        ),
        servers = {
                @Server(
                        description = "Prod ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "Dev ENV",
                        url = "https://crm-app.uz"
                )
        },
        security = {
                @SecurityRequirement(name = "bearerAuth")
//                @SecurityRequirement(name = "basicAuth")
        }
)

@SecurityScheme(
        name = "bearerAuth",
        description = "JWT scientification",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
//@SecurityScheme(
//        name = "basicAuth",
//        description = "Basic sc",
//        scheme = "basic",
//        type = SecuritySchemeType.HTTP,
//        in = SecuritySchemeIn.HEADER
//)
public class OpenApiConfig {

}
