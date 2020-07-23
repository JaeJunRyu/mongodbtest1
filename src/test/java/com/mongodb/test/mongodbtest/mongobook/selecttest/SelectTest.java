package com.mongodb.test.mongodbtest.mongobook.selecttest;

import com.mongodb.test.mongodbtest.config.MongoDBConfiguration;
import com.mongodb.test.mongodbtest.mongobook.domain.UserCol;
import com.mongodb.test.mongodbtest.mongobook.domain.UserColRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.TextCriteria;
import org.springframework.data.mongodb.core.query.TextQuery;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;


//맛있는 MongoDB 책 예제 실습
//@Import(MongoDBConfiguration.class)
//@DataMongoTest(includeFilters = { @ComponentScan.Filter(Service.class), @ComponentScan.Filter(Component.class), @ComponentScan.Filter(Repository.class) }
//        ,excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
//@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
@SpringBootTest(properties = "spring.autoconfigure.exclude=org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration")

public class SelectTest  {

    @Autowired
    private MongoOperations mongoOperations;

    @Autowired
    private UserColRepository userColRepository;

    @Test
    @DisplayName("키가 175이상 180 이하인 사람 조회")
    void select_test1(){
        Query query = new Query();
        query.addCriteria(
                where("height").gte(175).lte(180)
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("키가 180이상이면서 몸무게는 70 이하인 사람 조회")
    void select_test2(){
        Query query = new Query();
        query.addCriteria(
                where("height").gte(180)
        ).addCriteria(
                where("weight").lte(70)
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("name이 jayjun인 건 조회 eq")
    void select_test3(){
        Query query = new Query();
        query.addCriteria(
                where("name").is("jayjun")
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("name이 jayjun이 아닌건 조회 ne")
    void select_test4(){
        Query query = new Query();
        query.addCriteria(
                where("name").ne("jayjun")
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("height 가 180이상인 사람만 조회")
    void select_test5(){
        Query query = new Query();
        query.addCriteria(
                where("height").gt(180)
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("height 가 180보다 크거나 같은 사람만 조회")
    void select_test6(){
        Query query = new Query();
        query.addCriteria(
                where("height").gte(180)
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("height가 180보다 작은 사람 조회")
    void select_test7(){
        Query query = new Query();
        query.addCriteria(
                where("height").lt(180)
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("height가 180 작거나 같은 사람 조회")
    void select_test8(){
        Query query = new Query();
        query.addCriteria(
                where("height").lte(180)
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("category배열 중 지정한 조건에 일치하는 사람만 조회")
    void select_test9(){
        Query query = new Query();
        query.addCriteria(
                where("category").in("A","B")
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("category배열 중 지정한 조건에 일치하지 않는 사람만 조회")
    void select_test10(){
        Query query = new Query();
        query.addCriteria(
                where("category").nin("A","B")
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("category배열 중 지정한 조건에 일치하는 사람만 조회 - 정규표현식")
    void select_test11(){
        Query query = new Query();
        query.addCriteria(
                where("category").regex("^[A-B]")
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("category배열 중 지정한 조건에 일치하지 않는 사람만 조회 - 정규표현식")
    void select_test12(){
        Query query = new Query();
        query.addCriteria(
                where("category").not().regex("^[B]")
        );

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("180초과 10미만인 height 도큐먼트 구하기")
    void select_test14(){
        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("height").gt(180),Criteria.where("height").lt(10));
        Query query = new Query(criteria);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("180초과 10미만인 height 도큐먼트 구하기 - @Query 어노테이션 사용하기")
    void select_test15(){
        final List<UserCol> byHeightList = userColRepository.findByHeightList2(180,10);
        for (UserCol userCol : byHeightList) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("height가 180이상 190이하인 데이터 구하기")
    void select_test17(){
        Criteria criteria = new Criteria();
        criteria.andOperator(Criteria.where("height").gt(180),Criteria.where("height").lt(190));
        Query query = new Query(criteria);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("height가 180이상 190이하인 데이터 구하기 - @Query 어노테이션 사용하기")
    void select_test18(){
        final List<UserCol> byHeightList = userColRepository.findByHeightGtLt(180,190);
        for (UserCol userCol : byHeightList) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("$and [ $or[] , $or[] ] find 조건 만들기")
    void select_test19(){

        Criteria criteria = new Criteria();
        Criteria innerCriteria1 = new Criteria();
        Criteria innerCriteria2 = new Criteria();
        criteria.andOperator(
                innerCriteria1.orOperator(Criteria.where("height").gt(180),Criteria.where("height").lt(190)),
                innerCriteria2.orOperator(Criteria.where("height").gt(200),Criteria.where("height").lt(210))
        );
        Query query = new Query(criteria);

        System.out.println(query);
    }

    @Test
    @DisplayName("height가 180보다 큰 도큐먼트만 조회 하는데 $nor 연산자가 사용 되어서 조건에 만족하지 않는 도큐먼트가 조회 되었다.")
    void select_test20(){
        Criteria criteria = new Criteria();
        criteria.norOperator(Criteria.where("height").gt(180));
        Query query = new Query(criteria);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("정규 표현식 사용")
    void select_test21(){
        Criteria criteria = new Criteria();
        criteria.norOperator(Criteria.where("name").regex("jay"));
        Query query = new Query(criteria);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("text index 조회")
    void select_test22(){

        TextCriteria criteria = TextCriteria.forDefaultLanguage().matchingAny("\"Hello world\"").caseSensitive(true);
        TextQuery textQuery = new TextQuery(criteria);

        final List<UserCol> userCols = mongoOperations.find(textQuery, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("배열 조회1")
    void select_test23(){

//        Criteria criteria = Criteria.where("category").is(Arrays.asList("A","B"));
        Criteria criteria = Criteria.where("category").is(Arrays.asList("A","B","C"));
        Query query = new Query(criteria);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("배열 조회 - $elemMatch 연산자")
    void select_test24(){
        Criteria criteria1 =  new Criteria();
        criteria1.gte(6).lte(6);

        Criteria criteria = Criteria.where("category")
                .elemMatch(criteria1);

        Query query = new Query(criteria);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("배열 조회 - $all 연산자")
    void select_test25(){
//        Criteria criteria = Criteria.where("category").all("A","B");
        Criteria criteria = Criteria.where("category").all(Arrays.asList("A","B"));

        Query query = new Query(criteria);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("배열 조회 - $and 연산자")
    void select_test26(){

        Criteria criteria = new Criteria();
        criteria.andOperator(
              Criteria.where("category").is("A"),
              Criteria.where("category").is("B")
        );

        Query query = new Query(criteria);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("$all 연산자 와 $elemMatch 연산자 Query 만들기")
    void select_test27(){
        Criteria criteria = new Criteria();
        final Criteria criteria1 = where("size").is("M").and("num").gt(50);
        criteria.elemMatch(criteria1);

        Criteria criteria2 = new Criteria();
        criteria2.elemMatch(Criteria.where("num").is(100).and("color").is("green"));

        final Criteria object = where("object")
                .all(criteria.getCriteriaObject(), criteria2.getCriteriaObject());

        Query query = new Query(object);

        System.out.println(query);

        //Query: { "object" : { "$all" : [{ "$elemMatch" : { "size" : "M", "num" : { "$gt" : 50}}}, { "$elemMatch" : { "num" : 100, "color" : "green"}}]}}, Fields: {}, Sort: {}
    }



    @Test
    @DisplayName("배열 조회 - $size 연산자")
    void select_test28(){

        Criteria criteria = Criteria.where("category").size(3);

        Query query = new Query(criteria);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("프로젝션 연산자 - 점 연산자")
    void select_test29(){

        Query query = new Query();
        query.fields().include("object.firstname");
//        query.fields().include("object.firstname").exclude("object.last");

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            if(userCol.getUserProjection() != null){
                System.out.println(userCol.getUserProjection().toString());
            }
        }
    }


    @Test
    @DisplayName("프로젝션 연산자 - $slice")
    void select_test30(){

        Query query = new Query();
        query.fields().slice("category",1);

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("프로젝션 연산자 - $slice -n개")
    void select_test31(){

        Query query = new Query();
        query.fields().slice("category",-2).exclude("_id").exclude("name").exclude("height").exclude("weight");

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("프로젝션 연산자 - $slice [시작값 , 종료값]")
    void select_test32(){

        Query query = new Query();
        query.fields().slice("category",3,4).exclude("_id").exclude("name").exclude("height").exclude("weight");

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("프로젝션 연산자 배열 - objectarray에서 name이 abc인 도큐먼트 조회")
    void select_test33(){

        Query query = new Query();
        query.fields().elemMatch("objectarray",Criteria.where("name").is("abc"));

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }


    @Test
    @DisplayName("프로젝션 연산자 배열 - objectarray에서 age가 20인 도큐먼트 조회")
    void select_test34(){

        Query query = new Query();
        query.fields().elemMatch("objectarray",Criteria.where("age").is(20));

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }

    @Test
    @DisplayName("프로젝션 연산자 배열 - objectarray에서 age값이 10보다 큰 도큐먼트 조회")
    void select_test35(){

        Query query = new Query();
        query.fields().elemMatch("objectarray",Criteria.where("name").is("abc").and("age").gt(5));

        final List<UserCol> userCols = mongoOperations.find(query, UserCol.class);

        for (UserCol userCol : userCols) {
            System.out.println(userCol.toString());
        }
    }












































    @Test
    @DisplayName("컬렉션 페이징 구현 해보기")
    void select_test16(){
//        Pageable pageRequest = PageRequest.of(0, 3, Sort.Direction.DESC, "salary");
//        Query query = new Query();
//        query.with(pageRequest);
//        PageableExecutionUtils.getPage(byHeightList,pageRequest,() -> mongoOperations.count(query, UserCol.class));

    }








}
