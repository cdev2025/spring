package com.example.board;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BoardRestApiApplication {

	public static void main(String[] args) {
        SpringApplication.run(BoardRestApiApplication.class, args);

        System.out.println("\n" +
                "=================================================\n" +
                "🚀 Board REST API Application 시작 완료!\n" +
                "=================================================\n" +
                "📊 H2 Console: http://localhost:8080/h2-console\n" +
                "📋 Swagger UI: http://localhost:8080/swagger-ui/index.html\n" +
                "📖 API Docs: http://localhost:8080/v3/api-docs\n" +
                "🔗 REST API: http://localhost:8080/api/posts\n" +
                "✨ JPA Auditing 적용: 자동 시간/사용자 추적\n" +
                "=================================================\n");
    }
}
