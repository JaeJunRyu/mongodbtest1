package com.mongodb.test.mongodbtest.mongobook.inserttest;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DBObject;
import com.mongodb.test.mongodbtest.config.MongoDBConfiguration;
import com.mongodb.test.mongodbtest.mongobook.domain.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.data.mongodb.core.query.Query.query;
import static org.springframework.data.mongodb.core.query.Update.update;


//맛있는 MongoDB 책 예제 실습
//@ContextConfiguration(classes = {MongoDBConfiguration.class})
//@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
//@DataMongoTest
@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")
class InsertTestTest {

    @Autowired
    private MongoOperations mongoOperations;


    @Test
    @DisplayName("insert 쿼리 연습 - document 생성")
    @Transactional
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

    @Test
    @DisplayName("capped collection 생성 테스트")
    void create_mongodb_collection_capped() throws Exception{
        CollectionOptions options = CollectionOptions.empty()
                .capped()
                .size(1024)
                .maxDocuments(5);

        mongoOperations.createCollection("cappedTestCollection", options);
    }

    @Test
    @DisplayName("capped test 반복문으로 최대 사이즈 초과하게 만들기")
    void capped_test(){
        //given
        //when
        for (int i =0; i< 1000; i++){
            DBObject objectToSave = BasicDBObjectBuilder.start()
                    .add("x", i)
                    .get();

            mongoOperations.insert(objectToSave,"cappedCollection");
        }

        //then
        final List<DBObject> all = mongoOperations.findAll(DBObject.class,"cappedCollection");
        System.out.println(all.size());
        for (DBObject item:all ) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("java code insertMany test - domain Obj")
    @Transactional
    void insertMany(){
        //given
        TestObj testObj1 = new TestObj(1);
        TestObj testObj2 = new TestObj(2);
        TestObj testObj3 = new TestObj(3);

        List<TestObj> documents = new ArrayList<>();
        documents.add(testObj1);
        documents.add(testObj2);
        documents.add(testObj3);

        //when
        mongoOperations.insert(documents,TestObj.class);

        //then
        final List<TestObj> all = mongoOperations.findAll(TestObj.class);
        for (TestObj item:all ) {
            System.out.println(item);
        }
    }

    @Test
    @DisplayName("java code insertMany test - DB Obj")
    @Transactional
    void insertManyObject(){
        //given
        DBObject document1 = new BasicDBObject();
        document1.put("x",1);

        DBObject document2 = new BasicDBObject();
        document2.put("x",2);

        DBObject document3 = new BasicDBObject();
        document3.put("x",3);

        List<DBObject> documents = new ArrayList<>();
        documents.add(document1);
        documents.add(document2);
        documents.add(document3);

        //when
        mongoOperations.insert(documents,"manyCollection");

        //then

        final List<DBObject> all = mongoOperations.findAll(DBObject.class,"manyCollection");
        for (DBObject item:all ) {
            System.out.println(item);
        }
    }


    @Test
    @DisplayName("find 검색 테스트 - 파라미터 하나")
    void ticket_search(){
        Query query = new Query();
        query.addCriteria(Criteria.where("address").is("서울"));

        final List<TicketUser> ticketUsers = mongoOperations.find(query, TicketUser.class);
        for (TicketUser ticketUser : ticketUsers) {
            System.out.println(ticketUser.toString());
        }
    }


    @Test
    @DisplayName("find 검색 테스트 - 파라미터 둘")
    void ticket_search2(){
        Query query = new Query();
        query.addCriteria(Criteria.where("address").is("서울"))
                .addCriteria(Criteria.where("name").is("jay.moon"));

        final List<TicketUser> ticketUsers = mongoOperations.find(query, TicketUser.class);
        for (TicketUser ticketUser : ticketUsers) {
            System.out.println(ticketUser.toString());
        }
    }

    @Test
    @DisplayName("find 검색 테스트 - 점 연산자")
    void ticket_search3(){
        Query query = new Query();
        query.addCriteria(Criteria.where("name.firstName").is("jayjun"));

        final List<TicketUser> ticketUsers = mongoOperations.find(query, TicketUser.class);
        for (TicketUser ticketUser : ticketUsers) {
            System.out.println(ticketUser.toString());
        }
    }


    @Test
    @DisplayName("find 검색 테스트 - numbers 배열에서 값 찾기")
    void ticket_search4(){
        Query query = new Query();
        query.addCriteria(Criteria.where("numbers.0").is(52));

        final List<Numbers> numbersList = mongoOperations.find(query, Numbers.class);
        for (Numbers numbers : numbersList) {
            System.out.println(numbers.toString());
        }
    }


    @Test
    @DisplayName("updateOne을 사용해서 단건 데이터 업데이트 하기")
    void ticket_user_update(){
        //given

        //when
        //단건 업데이트 방법 1
        mongoOperations.updateFirst(
                query(Criteria.where("username").is("jayjun")),
                update("age",35),
                UserTest.class
        );
        //2020-07-15 14:19:20.889 DEBUG 71922 --- [           main] org.mongodb.driver.protocol.command      : Sending command '{"update": "userTest", "ordered": true, "$db": "ticket", "updates": [{"q": {"username": "jayjun"}, "u": {"$set": {"age": 35}}}]}' with request id 4 to database ticket on connection [connectionId{localValue:2, serverValue:3}] to server localhost:27017

        //단건 업데이트 방법 2
        mongoOperations.update(UserTest.class)
                .matching(Criteria.where("username").is("jayjun"))
                .apply(update("age",31))
                .first();

        //Sending command '{"update": "userTest", "ordered": true, "$db": "ticket", "updates": [{"q": {"username": "jayjun"}, "u": {"$set": {"age": 31}}}]}' with request id 5 to database ticket on connection [connectionId{localValue:2, serverValue:5}] to server localhost:27017

        //단건 업데이트 방법 3
        final UserTest andModifyValue = mongoOperations.update(UserTest.class)
                .matching(Criteria.where("username").is("jayjun"))
                .apply(update("age", 32))
                .findAndModifyValue();

        System.out.println(andModifyValue.toString());  //업데이트 전 오브젝트 값을 리턴 한다.
        //Sending command '{"findAndModify": "userTest", "query": {"username": "jayjun"}, "fields": {}, "new": false, "update": {"$set": {"age": 32}}, "$db": "ticket"}' with request id 6 to database ticket on connection [connectionId{localValue:2, serverValue:7}] to server localhost:27017

        final UserTest andModifyValue1 = mongoOperations.update(UserTest.class)
                .matching(Criteria.where("username").is("jayjun"))
                .apply(update("age", 32))
                .withOptions(FindAndModifyOptions.options().returnNew(true))  //업데이트된 값을 리턴 함
                .findAndModifyValue();

        System.out.println(andModifyValue1);
        //Sending command '{"findAndModify": "userTest", "query": {"username": "jayjun"}, "fields": {}, "new": true, "update": {"$set": {"age": 32}}, "$db": "ticket"}' with request id 6 to database ticket on connection [connectionId{localValue:2, serverValue:11}] to server localhost:27017
        //UserTest(id=5f0e8a31d4fc0d2f147b2be4, username=jayjun, age=32, address=경기, password=2222.0)

        //then

    }


    @Test
    @DisplayName("updateOne을 사용해서 여러건 데이터 업데이트 하기")
    void ticket_user_update_multi(){
        mongoOperations.updateMulti(
                query(Criteria.where("username").is("jayjun")),
                update("age",100),
                UserTest.class
        );
        //Sending command '{"update": "userTest", "ordered": true, "$db": "ticket", "updates": [{"q": {"username": "jayjun"}, "u": {"$set": {"age": 100}}, "multi": true}]}' with request id 4 to database ticket on connection [connectionId{localValue:2, serverValue:13}] to server localhost:27017
    }


    @Test
    @DisplayName("updateOne을 사용해서 여러건 데이터 업데이트 하기")
    void ticket_user_update_multi2(){

        Query query = new Query();
        query.addCriteria(Criteria.where("inventory").is("A"));

        final List<UserTestArray> userTestArrays = mongoOperations.find(
                query,
                UserTestArray.class
        );

        for (UserTestArray userTestArray : userTestArrays) {
            System.out.println(userTestArray.toString());
        }

        mongoOperations.updateMulti(query,
                update("inventory.$","changeA"),
                UserTestArray.class
        );

        final List<UserTestArray> userTestArrays1 = mongoOperations.find(
                query(Criteria.where("inventory").is("changeA")),
                UserTestArray.class
        );

        for (UserTestArray userTestArray : userTestArrays1) {
            System.out.println(userTestArray.toString());
        }
    }

    @Test
    @DisplayName("updateOne을 사용해서 여러건 데이터 업데이트 하기- 모든 파라미터 값 변경")
    void ticket_user_update_multi4(){

        Query query = new Query();
        query.addCriteria(Criteria.where("inventory").is("A"));

        final List<UserTestArray> userTestArrays = mongoOperations.find(
                query,
                UserTestArray.class
        );

        for (UserTestArray userTestArray : userTestArrays) {
            System.out.println(userTestArray.toString());
        }

        mongoOperations.updateMulti(query,
                update("inventory.$[]","changeA"),
                UserTestArray.class
        );

        final List<UserTestArray> userTestArrays1 = mongoOperations.find(
                query(Criteria.where("inventory").is("changeA")),
                UserTestArray.class
        );

        for (UserTestArray userTestArray : userTestArrays1) {
            System.out.println(userTestArray.toString());
        }
    }

    @Test
    @DisplayName("updateOne을 사용해서 여러건 데이터 업데이트 하기 - arrayFilters 사용하기")
    void ticket_user_update_multi3(){

        Query query = new Query();
        query.addCriteria(Criteria.where("inventory").is("A"));

        final List<UserTestArray> userTestArrays = mongoOperations.find(
                query,
                UserTestArray.class
        );

        for (UserTestArray userTestArray : userTestArrays) {
            System.out.println(userTestArray.toString());
        }

        mongoOperations.updateMulti(new Query(),
                update("inventory.$[changeElem]","changeA")
                        .filterArray("changeElem","A"),
                UserTestArray.class
        );

        final List<UserTestArray> userTestArrays1 = mongoOperations.find(
                query(Criteria.where("inventory").is("changeA")),
                UserTestArray.class
        );

        for (UserTestArray userTestArray : userTestArrays1) {
            System.out.println(userTestArray.toString());
        }
    }


    @Test
    @DisplayName("push test1")
    void push_test1(){
        //push test
        //new Update().push("category").each("spring", "data")
        mongoOperations.updateMulti(
                new Query(),
                new Update()
                     .push("category1")
                     .each("spring","data","mongoDB","gogo"),
                UserTestArray.class
                );
        //Sending command '{"update": "userTestArray", "ordered": true, "$db": "ticket", "updates": [{"q": {}, "u": {"$push": {"category1": {"$each": ["spring", "data", "mongoDB", "gogo"]}}}, "multi": true}]}' with request id 4 to database ticket on connection [connectionId{localValue:2, serverValue:47}] to server localhost:27017
    }


    @Test
    @DisplayName("push test2")
    void push_test2(){
        //push test
        //new Update().push("key").atPosition(Position.FIRST).each(Arrays.asList("Arya", "Arry", "Weasel"));
        mongoOperations.updateMulti(
                new Query(),
                new Update()
                        .push("key")
                        .atPosition(Update.Position.FIRST)
                        .each(Arrays.asList("Arya", "Arry", "Weasel")),
                UserTestArray.class
        );
    }


    @Test
    @DisplayName("push test3")
    void push_test3(){
        //push test
//        new Update().push("key").slice(5).each(Arrays.asList("Arya", "Arry", "Weasel"));

        mongoOperations.updateMulti(
                new Query(),
                new Update()
                        .push("key")
                        .slice(5)
                        .each(Arrays.asList("Arya", "Arry", "Weasel")),
                UserTestArray.class
        );
    }

    @Test
    @DisplayName("push test4")
    void push_test4(){
        //push test
        //new Update().addToSet("values").each("spring", "data", "mongodb");

        mongoOperations.updateMulti(
                new Query(),
                new Update()
                        .addToSet("values")
                        .each("spring", "data", "mongodb"),
                UserTestArray.class
        );
    }

    @Test
    @DisplayName("deleteOne 코드로 구현")
    void deleteOne_test1(){

        final String id = mongoOperations.findOne(query(Criteria.where("_id").is("5f0ec60ed4fc0d2f147b2c68")), UserTestArray.class).getId();
        System.out.println(id);

        mongoOperations.remove(query(Criteria.where("_id").is(id)), UserTestArray.class);
    }

    @Test
    @DisplayName("deleteMany 코드로 구현")
    void deleteOne_test2(){
        mongoOperations.remove(new Query(),UserTestArray.class);
    }

    @Test
    @DisplayName("트랜잭션 테스트 - 어노테이션 기반")
    @Transactional
    void transaction_test1(){

        TransactionTestObj transactionTestObj1 = new TransactionTestObj("test1","name1");
        TransactionTestObj transactionTestObj2 = new TransactionTestObj("test2","name2");
        TransactionTestObj transactionTestObj3 = new TransactionTestObj("test3","name3");

        List<TransactionTestObj> testObjs = new ArrayList<>();
        testObjs.add(transactionTestObj1);
        testObjs.add(transactionTestObj2);
        testObjs.add(transactionTestObj3);

        mongoOperations.insert(testObjs, TransactionTestObj.class);
    }







}