package com.mongodb.test.mongodbtest.customuser.service;

import com.mongodb.test.mongodbtest.config.MongoDBConfiguration;
import com.mongodb.test.mongodbtest.customuser.domain.QUser;
import com.mongodb.test.mongodbtest.customuser.infrastructure.mongo.UserRepositoryImpl;
import com.mongodb.test.mongodbtest.customuser.presentation.dto.CustomDto;
import com.querydsl.mongodb.morphia.MorphiaQuery;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mongo.embedded.EmbeddedMongoAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

//통합 테스트
@SpringBootTest(classes = MongoDBConfiguration.class)

//단위 테스트
//@Import(MongoDBConfiguration.class)
//@DataMongoTest(includeFilters = { @ComponentScan.Filter(Service.class), @ComponentScan.Filter(Component.class), @ComponentScan.Filter(Repository.class) }
//              ,excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)

//embedded
//@DataMongoTest(includeFilters = { @ComponentScan.Filter(Service.class), @ComponentScan.Filter(Component.class), @ComponentScan.Filter(Repository.class) })
class UserServiceTest {

    @Autowired
    private UserService userService;

    @DisplayName("repository를 활용한 save 테스트")
    @Test
    @Transactional
    void test1(){
        final var build = CustomDto.CustomRequestDto.builder()
                .name("test name5")
                .content("test content5")
                .startDate(LocalDateTime.of(2020, 7, 10, 13, 00, 00))
                .endDate(LocalDateTime.of(2020, 8, 10, 13, 00, 00))
                .build();

        userService.insert(build);
    }

    @DisplayName("mongoOperations를 활용한 insert 테스트")
    @Test
    @Transactional
    void test2(){
        final var build = CustomDto.CustomRequestDto.builder()
                .name("mongoOperations name")
                .content("mongoOperations test content")
                .startDate(LocalDateTime.of(2020, 7, 10, 13, 00, 00))
                .endDate(LocalDateTime.of(2020, 8, 10, 13, 00, 00))
                .build();

//        userService.customInsert(build);
    }

    @DisplayName("find by id test")
    @Test
    void findTest(){
        String id = "5f07efe26b748c7c3ce39598";
        final String byId = userService.findById(id);

        System.out.println(byId);
    }


    @Test
    @DisplayName("querydsl test")
    void user_querydsl_test1(){
        System.out.println("aaa");

        userService.queryDslTest();



    }




}