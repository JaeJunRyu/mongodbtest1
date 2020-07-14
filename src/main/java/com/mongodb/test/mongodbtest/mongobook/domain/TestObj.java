package com.mongodb.test.mongodbtest.mongobook.domain;

import lombok.Getter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "myCollection")
public class TestObj {

    private Integer x;

    public TestObj(Integer x) {
        this.x = x;
    }
}
