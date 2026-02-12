package br.com.sus.config;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;

@OpenAPIDefinition(
    info = @Info(
        title = "SUS SmartFlow API",
        version = "1.0.0",
        description = "API para triagem inteligente e prontuário unificado no SUS",
        contact = @Contact(
            name = "Equipe Hackathon",
            email = "contato@hackathon.com"
        ),
        license = @License(
            name = "MIT"
        )
    )
)
public class OpenApiConfig {
}
