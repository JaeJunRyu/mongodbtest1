package com.mongodb.test.mongodbtest.mongobook.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "userTest")
public class UserTest {

    @Id
    private String id;

    private String username;

    private Integer age;

    private String address;

    private String password;

    @Builder
    public UserTest( String username, Integer age, String address, String password) {
        this.username = username;
        this.age = age;
        this.address = address;
        this.password = password;
    }
}
