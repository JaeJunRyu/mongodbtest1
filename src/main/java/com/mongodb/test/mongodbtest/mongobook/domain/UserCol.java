package com.mongodb.test.mongodbtest.mongobook.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.index.TextIndexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "userCol")
public class UserCol {

    @Id
    private String id;

    @TextIndexed
    private String name;

    private Integer height;

    private Integer weight;

    private List<String> category;

    @Field(name = "object")
    private UserProjection userProjection;

    @Field(name ="objectarray")
    private List<UserProjectionArray> userProjectionArray;

    public UserCol(String name, Integer height, Integer weight, List<String> category) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.category = category;
    }
}
