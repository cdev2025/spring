package com.example.readingnoteapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing   // JPA Auditing 활성화 (자동 시간 관리)
public class ReadingNoteApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReadingNoteApiApplication.class, args);
	}

}
