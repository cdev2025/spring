package com.example.board.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Swaggerconfig {

    @Bean
    public OpenAPI openAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Board REST API")
                        .description("Spring Boot 기반 게시판 REST API 서비스\n\n" +
                                "**주요 기능:**\n" +
                                "- 게시글 CRUD 작업 \n" +
                                "- JOSN 기반 데이터 교환 \n" +
                                "- **JPA Auditing 적용:** createdAt, updatedAt, createdBy, updatedBy 자동 관리\n" +
                                "- BaseEntity를 통한 공통 Auditing 필드 관리\n\n" +
                                "**H2 Console:** http://localhost:8080/h2-console")
                        .version("v1.0.0")
                        .contact(new Contact()
                                .name("개발팀")
                                .email("dev@test.com")));
    }
}
