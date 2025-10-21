package com.example.readingnoteapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title="독서 노트 API",
                version = "v1.0",
                description = "내가 읽은 책과 감상을 기록하고 관리하는 REST API"
        ),
        servers = {
                @Server(url = "http://localhost:8080", description = "로컬 개발 서버")
        }
)
public class SwaggerConfig {
    // springdoc-openapi는 자동 서정. 별도 Bean 정의 불필요
}
