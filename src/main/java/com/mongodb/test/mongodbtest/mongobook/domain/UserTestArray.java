package com.mongodb.test.mongodbtest.mongobook.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "userTestArray")
public class UserTestArray {

    @Id
    private String id;

    private String username;

    private Integer age;

    private String address;

    private String password;

    private List<String> inventory;

}
