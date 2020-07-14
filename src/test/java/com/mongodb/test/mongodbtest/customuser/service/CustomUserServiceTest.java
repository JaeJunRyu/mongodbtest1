package com.mongodb.test.mongodbtest.customuser.service;

import com.mongodb.test.mongodbtest.customuser.presentation.dto.CustomDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
//@DataMongoTest
class CustomUserServiceTest {

    @Autowired
    private CustomUserService customUserService;


    @DisplayName("repository를 활용한 save 테스트")
    @Test
    void test1(){


        final var build = CustomDto.CustomRequestDto.builder()
                .name("test name")
                .content("test content")
                .startDate(LocalDateTime.of(2020, 7, 10, 13, 00, 00))
                .endDate(LocalDateTime.of(2020, 8, 10, 13, 00, 00))
                .build();

        customUserService.insert(build);
    }

//    @DisplayName("mongoOperations를 활용한 insert 테스트")
//    @Test
//    void test2(){
//        final var build = CustomDto.CustomRequestDto.builder()
//                .name("mongoOperations name")
//                .content("mongoOperations test content")
//                .startDate(LocalDateTime.of(2020, 7, 10, 13, 00, 00))
//                .endDate(LocalDateTime.of(2020, 8, 10, 13, 00, 00))
//                .build();
//
//        customUserService.customInsert(build);
//    }


    @DisplayName("find by id test")
    @Test
    void findTest(){
        String id = "5f07efe26b748c7c3ce39598";
        final String byId = customUserService.findById(id);

        System.out.println(byId);
    }




}