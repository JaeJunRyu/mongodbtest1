package com.mongodb.test.mongodbtest.crud.domain.ticket;

import lombok.Getter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

@Getter
public class TicketProductList {

    @Id
    private ObjectId ticketProductId;

    private String detailContent;

    public TicketProductList() {
        this.ticketProductId = ObjectId.get();
    }
}
