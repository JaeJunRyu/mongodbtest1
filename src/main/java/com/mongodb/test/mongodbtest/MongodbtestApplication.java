package com.mongodb.test.mongodbtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.mongodb.config.EnableMongoAuditing;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.Optional;

@SpringBootApplication
@EnableMongoAuditing(dateTimeProviderRef = "utcDateTimeProvider")
public class MongodbtestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MongodbtestApplication.class, args);
    }

    @Bean
    public DateTimeProvider utcDateTimeProvider() {
//        return () -> Optional.of(LocalDateTime.now(ZoneOffset.UTC));
        return () -> Optional.of(LocalDateTime.now(ZoneId.of("Asia/Seoul")));
    }
}
