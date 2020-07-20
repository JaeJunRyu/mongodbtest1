package com.mongodb.test.mongodbtest.customuser.domain;

import com.mongodb.test.mongodbtest.crud.domain.BaseEntity;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "user")
@ToString
public class User extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "customUser_sequence";

    @Indexed(unique = true)
    private Long seq;

    private String name;

    private String content;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    @Builder
    public User(Long seq, String name, String content, LocalDateTime startDate, LocalDateTime endDate) {
        this.seq = seq;
        this.name = name;
        this.content = content;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
