package com.mongodb.test.mongodbtest.mongobook.selecttest;

import com.mongodb.MongoQueryException;
import com.mongodb.test.mongodbtest.config.MongoDBConfiguration;
import com.mongodb.test.mongodbtest.mongobook.domain.QUserCol;
import com.mongodb.test.mongodbtest.mongobook.domain.UserCol;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.mongodb.morphia.MorphiaQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;


//맛있는 MongoDB 책 예제 실습
@Import(MongoDBConfiguration.class)
@DataMongoTest(includeFilters = { @ComponentScan.Filter(Service.class), @ComponentScan.Filter(Component.class), @ComponentScan.Filter(Repository.class) }
              ,excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class SelectTest  {

    @Autowired
    private MongoOperations mongoOperations;

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


}
