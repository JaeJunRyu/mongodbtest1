package com.mongodb.test.mongodbtest.mongobook;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "rating")
public class MapReduce {

    @Id
    private Integer _id;

    private double rating;

    private double user_id;

    private String hello;

    public MapReduce(Integer _id, double rating, double user_id) {
        this._id = _id;
        this.rating = rating;
        this.user_id = user_id;
    }
}
