package com.mongodb.test.mongodbtest.mongobook.inserttest;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.test.mongodbtest.config.MongoDBConfiguration;
import com.mongodb.test.mongodbtest.mongobook.domain.TestObj;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;


import static org.junit.jupiter.api.Assertions.*;


//맛있는 MongoDB 책 예제 실습
@ContextConfiguration(classes = {MongoDBConfiguration.class})
@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
//@DataMongoTest
class InsertTestTest {

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    @DisplayName("insert 쿼리 연습 - document 생성")
    void mongodb_insert_test1(){
        //given
        TestObj testObj = new TestObj(1);

        //when
        mongoOperations.insert(testObj);
        Query query = new Query();
        query.addCriteria(Criteria.where("x").is(1));

        final TestObj one = mongoOperations.findOne(query, TestObj.class);

        //then
        assertEquals( 1, one.getX());
    }

    @Test
    @DisplayName("insert 쿼리 연습 - DBObject로 값 할당")
    void mongodb_insert_test2(){
        //given
        DBObject objectToSave = BasicDBObjectBuilder.start()
                .add("x", 1)
                .get();

        System.out.println(objectToSave);

        //when
        mongoOperations.insert(objectToSave, "myCollection");
        Query query = new Query();
        query.addCriteria(Criteria.where("x").is(1));
        final DBObject one = mongoOperations.findOne(query, DBObject.class,"myCollection");

        //then
        assertEquals(1, one.get("x"));
    }


}