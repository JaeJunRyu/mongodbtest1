package com.mongodb.test.mongodbtest.mongobook.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Document(collection = "transactionTestCol")
public class TransactionTestObj {

    @Id
    private String id;

    private String content;

    private String name;

    public TransactionTestObj( String content, String name) {
        this.content = content;
        this.name = name;
    }
}
