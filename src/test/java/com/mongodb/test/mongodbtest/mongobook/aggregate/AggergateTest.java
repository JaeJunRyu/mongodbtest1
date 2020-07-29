package com.mongodb.test.mongodbtest.mongobook.aggregate;

import com.mongodb.BasicDBObject;
import com.mongodb.internal.operation.AggregateOperation;
import com.mongodb.test.mongodbtest.aggregation.CustomAggregationOperation;
import com.mongodb.test.mongodbtest.mongobook.MapReduce;
import com.mongodb.test.mongodbtest.mongobook.aggregate.dto.AggregateResponseDto;
import org.bson.Document;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

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

        //        LookupOperation lookupOperation = LookupOperation.newLookup().from().localField().foreignField().as()
        final LookupOperation lookup = lookup("inventory", "item", "sku", "inventory_docs");
        Aggregation aggregation = Aggregation.newAggregation(lookup);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation, "orders", HashMap.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


    @Test
    @DisplayName("aggregate $lookup를 사용 해보기 - 두번째 방법")
    void aggregateTest13(){

        final HashMap<String, Object> letMap = new HashMap<>();
        letMap.put("order_item","$item");
        letMap.put("order_qty","$ordered");

        final HashMap<String, Object> projectMap = new HashMap<>();
        projectMap.put("stock_item",0);
        projectMap.put("_id",0);

        final List<Document> documentList = Arrays.asList(
                new Document("$match",
                        new Document("$expr",
                                new Document("$and", Arrays.asList(
                                        new Document("$eq", Arrays.asList("$stock_item", "$$order_item")),
                                        new Document("$gte", Arrays.asList("$instock", "$$order_qty"))
                                )
                                )
                        )
                )

        );

        final List<Document> pipelinelist = new ArrayList<>();
        pipelinelist.addAll(documentList);
        pipelinelist.add(new Document("$project",projectMap));

        Aggregation aggregation1 = Aggregation.newAggregation( l ->
                new Document("$lookup",
                    new Document("from", "warehouses")
                        .append("let", letMap )
                        .append("pipeline",pipelinelist )
                        .append("as", "stockdata")
                ));

        System.out.println(aggregation1);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation1, "orders", HashMap.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }

    }

    @Test
    @DisplayName("aggregate $replaceRoot를 사용 해보기")
    void aggregateTest14(){
        final UnwindOperation unwindOperation = unwind("$grades");
        final MatchOperation matchOperation = match(Criteria.where("grades.grade").gte(90));
        final ReplaceRootOperation replaceRootOperation = replaceRoot("$grades");

        Aggregation aggregation1 = Aggregation.newAggregation(unwindOperation,matchOperation,replaceRootOperation);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation1, "students", HashMap.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }

//        {_id=1.0, grades={test=3.0, grade=95.0, mean=85.0, std=6.0}}
//        {_id=2.0, grades={test=1.0, grade=90.0, mean=75.0, std=6.0}}
//        {_id=2.0, grades={test=3.0, grade=91.0, mean=85.0, std=4.0}}

//        {std=6.0, test=3.0, mean=85.0, grade=95.0}
//        {std=6.0, test=1.0, mean=75.0, grade=90.0}
//        {std=4.0, test=3.0, mean=85.0, grade=91.0}
    }

    @Test
    @DisplayName("aggregate $sample를 사용 해보기")
    void aggregateTest15(){

        final SampleOperation sample = sample(3);

        Aggregation aggregation1 = Aggregation.newAggregation(sample);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation1, "rating", HashMap.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


    @Test
    @DisplayName("aggregate $sortByCount를 사용 해보기")
    void aggregateTest16(){

        final SortByCountOperation sortByCountOperation = sortByCount("$rating");

        Aggregation aggregation1 = Aggregation.newAggregation(sortByCountOperation);

        final AggregationResults<HashMap> rating = mongoOperations.aggregate(aggregation1, "rating", HashMap.class);
        final List<HashMap> mappedResults = rating.getMappedResults();

        for (HashMap mappedResult : mappedResults) {
            System.out.println(mappedResult);
        }
    }


    @Test
    void aggregationTest(){

        Aggregation aggregation111 = Aggregation.newAggregation(
                match(new Criteria()),
                //lookup("users", "postedBy", "_id", "user")
                new AggregationOperation() {
                    @Override
                    public Document toDocument(AggregationOperationContext context) {
                        return new Document("$lookup",
                                new Document("from", "users")
                                        .append("let", new Document("postedBy", "$postedBy"))
                                        .append("pipeline", Arrays.asList(
                                                new Document("$match",
                                                        new Document("$expr",
                                                                new Document("$eq", Arrays.asList(
                                                                        new Document("$toString", "$_id"),
                                                                        "$$postedBy"
                                                                ))))))
                                        .append("as", "user"));
                    }
                },
                unwind("$user"),
                new AggregationOperation() {

                    @Override
                    public Document toDocument(AggregationOperationContext context) {
                        return new Document("$addFields",
                                new Document("id", new Document("$toString", "$_id"))
                                        .append("username", "$user.name")
                                        .append("upvotes", new Document("$size", "$upvotesBy"))
                                        .append("isUpvoted", new Document("$in", Arrays.asList("", "$upvotesBy")))
                                        .append("isPinned", new Document("$cond",
                                                Arrays.asList(new Document("$gte",
                                                        Arrays.asList(new Document("$size", "$upvotesBy"), 3)), Boolean.TRUE, Boolean.FALSE)))
                                        .append("createdAt", new Document("$dateToString",
                                                new Document("format", "%H:%M %d-%m-%Y")
                                                        .append("timezone", "+01")
                                                        .append("date", "$createdAt")
                                        )));
                    }
                },
                sort(Sort.Direction.DESC, "isPinned", "createdAt"),
                project().andExclude("user", "_class")
        );


        final Document document1 = new Document("$lookup",
                new Document("from", "users")
                        .append("let", new Document("postedBy", "$postedBy"))
                        .append("pipeline", Arrays.asList(
                                new Document("$match",
                                        new Document("$expr",
                                                new Document("$eq", Arrays.asList(
                                                        new Document("$toString", "$_id"),
                                                        "$$postedBy"
                                                ))))))
                        .append("as", "user"));

        final Document document2 = new Document("$lookup",
                new Document("from", "users")
                        .append("let", new Document("postedBy", "$postedBy"))
                        .append("pipeline", Arrays.asList(
                                new Document("$match",
                                        new Document("$expr",
                                                new Document("$eq", Arrays.asList(
                                                        new Document("$toString", "$_id"),
                                                        "$$postedBy"
                                                ))))))
                        .append("as", "user"));

        List<Document> documentList = new LinkedList<>();
        documentList.add(document1);
        documentList.add(document2);

        CustomAggregationOperation customAggregationOperation = new CustomAggregationOperation(documentList);

        Aggregation aggregation = Aggregation.newAggregation(customAggregationOperation);


        System.out.println(aggregation);

        Aggregation aggregation1 = Aggregation.newAggregation( l -> new Document("$lookup",
                new Document("from", "users")
                        .append("let", new Document("postedBy", "$postedBy"))
                        .append("pipeline", Arrays.asList(
                                new Document("$match",
                                        new Document("$expr",
                                                new Document("$eq", Arrays.asList(
                                                        new Document("$toString", "$_id"),
                                                        "$$postedBy"
                                                ))))))
                        .append("as", "user")));

        System.out.println(aggregation1);

    }

}



