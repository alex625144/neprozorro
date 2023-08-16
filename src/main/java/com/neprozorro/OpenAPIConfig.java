package com.neprozorro;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Neprozorro",
                        email = "${info.contacts.email}"
                      //  url = "https://neprozorro/com"
                ),
                description = "OpenAPI documentation for NEPROZORRO",
                title = "OpenAPI specification - NEPROZORRO",
                version = "${info.version}"
//                license = @License(
//                        name = "License name",
//                        url = "https://.../com"
//                ),
               // termsOfService = "Term of service = Условия использования"
        ),
        servers = {
                @Server(
                        description = "neprozorro",
                        url = "http://localhost:${server.port}"
                )
        }
)
public class OpenAPIConfig {
}
