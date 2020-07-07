package com.mongodb.test.mongodbtest.crud.domain.ticket;

import com.mongodb.test.mongodbtest.crud.domain.BaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@NoArgsConstructor
@Document(collection = "ticketProduct")
public class TicketProduct extends BaseEntity {

    @Transient
    public static final String SEQUENCE_NAME = "ticketProduct_sequence";

    @Indexed(unique = true)
    private Long seq;

    private String productName;

    private Integer price;

    private String content;

    public TicketProduct(Long seq, String productName, Integer price, String content) {
        this.seq = seq;
        this.productName = productName;
        this.price = price;
        this.content = content;
    }

}
