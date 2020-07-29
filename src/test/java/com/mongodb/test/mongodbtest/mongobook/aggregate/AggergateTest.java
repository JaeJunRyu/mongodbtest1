package com.mongodb.test.mongodbtest.mongobook.aggregate;

import com.mongodb.test.mongodbtest.mongobook.MapReduce;
import com.mongodb.test.mongodbtest.mongobook.aggregate.dto.AggregateResponseDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashMap;
import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;
import static org.springframework.data.mongodb.core.aggregation.Fields.field;


@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
public class AggergateTest {

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @DisplayName("aggregate 테스트")
    void aggregateTest() {

        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.project("rating").andExclude("_id")
        );

        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, "rating", AggregateResponseDto.class);

        final List<AggregateResponseDto> mappedResults = rating.getMappedResults();

        for (AggregateResponseDto mappedResult : mappedResults) {
            System.out.println(mappedResult.toString());
        }
    }

    @Test
    @DisplayName("aggregate $project를 활용해서 새로운 필드 추가하기 테스트")
    void aggregateNewField(){

        final ProjectionOperation projectionOperation = Aggregation.project()
                .andInclude("rating")
                .andInclude("hello").andArrayOf("new field").as("hello")
                .andExclude("_id");

        Aggregation aggregation = Aggregation.newAggregation(projectionOperation);

        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, "rating", AggregateResponseDto.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<AggregateResponseDto> mappedResults = rating.getMappedResults();
        for (AggregateResponseDto mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }

    }


    @Test
    @DisplayName("aggregate $project를 활용해서 $multiply 해보기")
    void aggregateTest1(){

        final ProjectionOperation projectionOperation = Aggregation.project()
                .and("$_id").multiply("$user_id").as("multiplyval")
                .andExpression("$_id * $user_id * $rating").as("multiplytest")
                .andExclude("_id");


        Aggregation aggregation = Aggregation.newAggregation(projectionOperation);

        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, "rating", AggregateResponseDto.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<AggregateResponseDto> mappedResults = rating.getMappedResults();

        for (AggregateResponseDto mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }

    }


    @Test
    @DisplayName("aggregate $group를 활용해서 $sum 해보기")
    void aggregateTest2(){


        final GroupOperation.GroupOperationBuilder groupOperationBuilder = group("$rating").count();

        Aggregation aggregation = Aggregation.newAggregation(groupOperationBuilder.as("count"));

        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, "rating", AggregateResponseDto.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<AggregateResponseDto> mappedResults = rating.getMappedResults();

        for (AggregateResponseDto mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }

    }


    @Test
    @DisplayName("aggregate $group를 활용해서 $first 해보기")
    void aggregateTest3(){


        final SortOperation sortOperation = Aggregation.sort(Sort.Direction.DESC, "$user_id");

        final GroupOperation.GroupOperationBuilder groupOperationBuilder = group("$rating").first("$_id");

        Aggregation aggregation = Aggregation.newAggregation(sortOperation,groupOperationBuilder.as("first"));

        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, "rating", AggregateResponseDto.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<AggregateResponseDto> mappedResults = rating.getMappedResults();

        for (AggregateResponseDto mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }

    }



    @Test
    @DisplayName("aggregate $match를 사용 해보기")
    void aggregateTest4(){

        final MatchOperation match = match(Criteria.where("rating").gte(4));
        final GroupOperation group = group("$rating").push("user_id").as("user_ids");

        Aggregation aggregation = Aggregation.newAggregation(match,group);

        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, "rating", AggregateResponseDto.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<AggregateResponseDto> mappedResults = rating.getMappedResults();

        for (AggregateResponseDto mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


    @Test
    @DisplayName("aggregate $unwinds를 사용 해보기")
    void aggregateTest5(){

        final MatchOperation match = match(Criteria.where("rating").gte(4));
        final GroupOperation group = group("$rating").push("user_id").as("user_ids");
        final UnwindOperation unwindOperationn = unwind("$user_ids");

        Aggregation aggregation = Aggregation.newAggregation(match,group,unwindOperationn);

        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, "rating", AggregateResponseDto.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<AggregateResponseDto> mappedResults = rating.getMappedResults();

        for (AggregateResponseDto mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


    @Test
    @DisplayName("aggregate $out를 사용 해보기")
    void aggregateTest6(){

        final GroupOperation group = group("$rating").push("user_id").as("user_ids");
        final OutOperation user_ids_by_rating = out("user_ids_by_rating");

        Aggregation aggregation = Aggregation.newAggregation(group,user_ids_by_rating);

        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, "rating", AggregateResponseDto.class);
    }



    @Test
    @DisplayName("aggregate $sort, $limit, $skip를 사용 해보기")
    void aggregateTest7(){

        final SortOperation sort = sort(Sort.Direction.DESC, "$user_id");
        final LimitOperation limit = limit(5);
        final SkipOperation skip = skip(1);

        Aggregation aggregation = Aggregation.newAggregation(sort,limit,skip);

        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, "rating", AggregateResponseDto.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<AggregateResponseDto> mappedResults = rating.getMappedResults();

        for (AggregateResponseDto mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


    @Test
    @DisplayName("aggregate $bucket를 사용 해보기")
    void aggregateTest8(){

        final BucketOperation bucketOperation = bucket("$rating")
                .withBoundaries(2, 3, 5)
                .withDefaultBucket("Others")
                .andOutput("user_ids").count().as("count")
                .andOutput("user_id").push().as("user_ids");


        Aggregation aggregation = Aggregation.newAggregation(bucketOperation);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation, "rating", HashMap.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


    @Test
    @DisplayName("aggregate $addFields를 사용 해보기")
    void aggregateTest9(){

        final AddFieldsOperation build = addFields().addField("hello").withValue("hello11").build();
        final ProjectionOperation projectionOperation = project().andInclude("hello");
        final LimitOperation limit = limit(3);

        Aggregation aggregation = Aggregation.newAggregation(build,projectionOperation,limit);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation, "rating", HashMap.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }

    @Test
    @DisplayName("aggregate $addFields를 사용 해보기 - 점 연산자 사용하기")
    void aggregateTest10(){

        final AddFieldsOperation build = addFields().addField("hi.hello").withValue("world").build();
        final ProjectionOperation projectionOperation = project().and("hi").nested(Fields.from(field("hello")));
        final LimitOperation limit = limit(3);

        Aggregation aggregation = Aggregation.newAggregation(build,projectionOperation,limit);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation, "rating", HashMap.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


    @Test
    @DisplayName("aggregate $facet를 사용 해보기")
    void aggregateTest11(){

        final FacetOperation as = facet(
                group("$rating").count().as("count")
        ).as("categorizedByRating")
                .and(bucketAuto("$_id", 5)).as("categorizedById");

        Aggregation aggregation = Aggregation.newAggregation(as);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation, "rating", HashMap.class);
//        final AggregationResults<AggregateResponseDto> rating = mongoOperations.aggregate(aggregation, MapReduce.class, AggregateResponseDto.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


    @Test
    @DisplayName("aggregate $lookup를 사용 해보기 - 첫번째 방법")
    void aggregateTest12(){

        final LookupOperation lookup = lookup("inventory", "item", "sku", "inventory_docs");
        Aggregation aggregation = Aggregation.newAggregation(lookup);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation, "orders", HashMap.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


}



