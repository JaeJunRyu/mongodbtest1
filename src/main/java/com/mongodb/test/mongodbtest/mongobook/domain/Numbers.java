package com.mongodb.test.mongodbtest.mongobook.domain;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter
@Document(collection = "B")
@ToString
public class Numbers {

    @Id
    private String id;

    private List<Integer> numbers;

}
