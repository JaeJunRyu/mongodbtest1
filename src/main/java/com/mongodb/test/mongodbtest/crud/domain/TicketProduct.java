package com.mongodb.test.mongodbtest.crud.domain;


import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "ticketProduct")
public class TicketProduct {

    @Id
    private String id;

    private String productName;

    private Integer price;

    private String content;

    @Builder
    public TicketProduct(String productName, Integer price, String content) {
        this.productName = productName;
        this.price = price;
        this.content = content;
    }
}
