package com.mongodb.test.mongodbtest.crud.presentation.web.dto;

import com.mongodb.test.mongodbtest.crud.domain.ticket.TicketProduct;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
public class TicketProductSaveRequestDto {

    private String productName;
    private Integer price;
    private String content;

    @Builder
    public TicketProductSaveRequestDto(String productName, Integer price, String content) {
        this.productName = productName;
        this.price = price;
        this.content = content;
    }

    //    @Builder(builderMethodName = "hiddenBuilder")
//    public TicketProductSaveRequestDto(String productName, Integer price, String content) {
//        this.productName = productName;
//        this.price = price;
//        this.content = content;
//    }
//
//    public static TicketProductSaveRequestDtoBuilder builder(String productName) {
//        return hiddenBuilder()
//                .productName(productName);
//    }



//     public TicketProduct toEntity(){
//        return TicketProduct.builder()
//                .productName(productName)
//                .content(content)
//                .price(price)
//                .build();
//    }

    public TicketProduct toEntity(Long seq) {
        return new TicketProduct(seq, productName, price, content);
    }


}
