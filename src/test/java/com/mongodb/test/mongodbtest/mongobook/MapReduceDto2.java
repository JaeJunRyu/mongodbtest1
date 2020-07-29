package com.mongodb.test.mongodbtest.mongobook;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MapReduceDto2 {

    private Integer _id;
    private MapReduceValue value;

    @Getter
    @Setter
    @ToString
    public class MapReduceValue{
        private Integer count;
        private Integer user_id_sum;
        private double user_id_avg;
    }
}
