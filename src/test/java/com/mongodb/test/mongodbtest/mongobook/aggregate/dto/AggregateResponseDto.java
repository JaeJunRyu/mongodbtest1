package com.mongodb.test.mongodbtest.mongobook.aggregate.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class AggregateResponseDto {

    private Integer _id;
    private Integer rating;
    private String hello;
    private Integer multiplyval;
    private Integer multiplytest;
    private Integer count;
    private Integer first;
    private List<Integer> user_ids;
    private Integer user_id;

}
