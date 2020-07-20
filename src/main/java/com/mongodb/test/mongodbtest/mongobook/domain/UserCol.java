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
@Document(collection = "userCol")
public class UserCol {

    @Id
    private String id;

    private String name;

    private Integer height;

    private Integer weight;

    private List<String> category;

    public UserCol(String name, Integer height, Integer weight, List<String> category) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.category = category;
    }
}
