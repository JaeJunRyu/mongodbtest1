package com.mongodb.test.mongodbtest.mongobook;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.mapreduce.MapReduceOptions;
import org.springframework.data.mongodb.core.mapreduce.MapReduceResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.sortByCount;


@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class MapReduceTest {

    @Autowired
    private MongoOperations mongoOperations;

    private static final String MAP_FUNCTION = "function(){ emit(this.rating, this.user_id) }";
    private static final String MAP_FUNCTION1 = "function(){ emit(this.rating, 1) }";
    private static final String MAP_FUNCTION2 = "function(){ emit(this.rating, {count:1, user_id_sum : this.user_id }) }";

    private static final String REDUCE_FUNCTION = "function(key, values) { return values.length }";
//    private static final String REDUCE_FUNCTION1 = "function(key, values) { var counter=0 values.forEach(function(value) { counter+=value }) return counter }";
    private static final String REDUCE_FUNCTION1 = "function(key, values) { var counter=0; values.forEach(function(value){ counter+=value; }); return counter; }";
    private static final String REDUCE_FUNCTION2 = "function(key, values) { var sum = 0; var counter = 0; values.forEach(function(document){ sum+= document.user_id_sum; counter += document.count }); return {count:counter, user_id_sum:sum}; }";

    private static final String FINALIZE_FUNCTION = "function(key, value) { value.user_id_avg = value.user_id_sum/value.count; return value; }";

    @Test
    @DisplayName("맵-리듀스 테스트")
    void map_reduceTest(){
        final MapReduceResults<MapReduceDto> rating = mongoOperations.mapReduce("rating", MAP_FUNCTION, REDUCE_FUNCTION, MapReduceDto.class);

        for (MapReduceDto mapReduceDto : rating) {
            System.out.println(mapReduceDto.toString());
        }
    }

    @Test
    @DisplayName("맵-리듀스 테스트 - 수정된 맵핑함수")
    void map_reduceTest1(){
        final MapReduceResults<MapReduceDto> rating = mongoOperations.mapReduce("rating", MAP_FUNCTION1, REDUCE_FUNCTION, MapReduceDto.class);

        for (MapReduceDto mapReduceDto : rating) {
            System.out.println(mapReduceDto.toString());
        }
    }


    @Test
    @DisplayName("맵-리듀스 테스트 - 데이터가 많을 경우 리듀스 함수 멱등법칙을 유지하기 위해서 리듀스 함수 수정")
    void map_reduceTest2(){
        final MapReduceResults<MapReduceDto> rating = mongoOperations.mapReduce("rating", MAP_FUNCTION1, REDUCE_FUNCTION1, MapReduceDto.class);

        for (MapReduceDto mapReduceDto : rating) {
            System.out.println(mapReduceDto.toString());
        }
    }

    @Test
    @DisplayName("맵-리듀스 테스트 - Rating 컬렉션에 id값이 6 이하이면서\n" +
            "      id 값이 가장 큰 4개의 도큐먼트를 rating별로 분류한 다음\n" +
            "     각각의 도큐먼트의 개수와\n" +
            "     user_id 값들의 합과\n" +
            "     user_id의 평균을\n" +
            "     구해보자\n")
    void map_reduceTest3(){

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").lte(6)).with(Sort.by(Sort.Direction.DESC, "_id"));

        MapReduceOptions mapReduceOptions = new MapReduceOptions();
        mapReduceOptions.limit(4);
        mapReduceOptions.finalizeFunction(FINALIZE_FUNCTION);

        final MapReduceResults<MapReduceDto2> rating = mongoOperations.mapReduce(query,"rating", MAP_FUNCTION2, REDUCE_FUNCTION2, mapReduceOptions, MapReduceDto2.class);
//        final MapReduceResults<HashMap> rating = mongoOperations.mapReduce(query, "rating", MAP_FUNCTION2, REDUCE_FUNCTION2, mapReduceOptions, HashMap.class);

        for (MapReduceDto2 mapReduceDto : rating) {
            System.out.println(mapReduceDto.toString());
        }
    }




    @Test
    @DisplayName("맵-리듀스 테스트 - 증분 맵-리듀스 mapReduceRating 컬렉션에 데이터 저장")
    void map_reduceTest4(){
        MapReduceOptions mapReduceOptions = new MapReduceOptions();
        mapReduceOptions.outputCollection("mapReduceRating");

        mongoOperations.mapReduce("rating", MAP_FUNCTION1, REDUCE_FUNCTION1,mapReduceOptions, MapReduceDto.class);
    }

    @Test
    @DisplayName("맵-리듀스 테스트 - 증분 맵-리듀스 rating컬렉션에 5000개 데이터 추가")
    void map_reduceTest5(){

        for (int i=0; i<5000; i++ ){
            final double ratingVal = Math.floor(Math.random() * 5) + 1;
            final double user_idVal = Math.floor(Math.random() * 100) + 1;

            MapReduce mapReduce = new MapReduce(i + 11, ratingVal , user_idVal);

            mongoOperations.insert(mapReduce);
        }
    }


    @Test
    @DisplayName("맵-리듀스 테스트 - 증분 맵-리듀스 mapReduceRating 컬렉션에 데이터 저장")
    void map_reduceTest6(){

        Query query = new Query();
        query.addCriteria(Criteria.where("_id").gt(10));
        MapReduceOptions mapReduceOptions = new MapReduceOptions();
        mapReduceOptions.outputCollection("mapReduceRating");

        mongoOperations.mapReduce(query,"rating", MAP_FUNCTION1, REDUCE_FUNCTION1, mapReduceOptions, MapReduceDto.class);
    }

}
