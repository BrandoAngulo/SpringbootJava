package integradorrecaudo.utilidades.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.http.HttpHeaders;

@OpenAPIDefinition(
        info = @Info(
                title = "API INTEGRADOR DE RECAUDO",
                description = "Integra el recaudo de los recaudadores recaudados",
                version = "1.0.0",
                contact = @Contact(
                        name = "Playtech",
                        url = "https://playtechla.com",
                        email = "playtech@playtechla.com"
                )
        ),
        servers = {
                @Server(
                        description = "DEV SERVER",
                        url = "http://192.168.1.129:9090/api/integradorrecaudo"
                ),
                @Server(
                        description = "PROD SERVER",
                        url = "http://unprogramadornace:8080"
                )
        },
        security = @SecurityRequirement(
                name = "Security Token"
        )
)
@SecurityScheme(
        name = "Security Token",
        description = "Access Token For My API",
        type = SecuritySchemeType.HTTP,
        paramName = HttpHeaders.AUTHORIZATION,
        in = SecuritySchemeIn.HEADER,
        scheme = "Bearer",
        bearerFormat = "JWT"
)
public class SwaggerConfig {
}

