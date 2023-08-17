package com.neprozorro.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Neprozorro",
                        email = "${info.contacts.email}"
                ),
                description = "OpenAPI documentation for NEPROZORRO",
                title = "OpenAPI specification - NEPROZORRO",
                version = "${info.version}"
        ),
        servers = {
                @Server(
                        description = "neprozorro",
                        url = "${info.host}"
                )
        }
)

public class OpenAPIConfig {
}
