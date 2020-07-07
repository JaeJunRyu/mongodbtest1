package com.mongodb.test.mongodbtest.crud.presentation.web;

import com.mongodb.test.mongodbtest.crud.presentation.web.dto.CommandDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureDataMongo
@WebMvcTest(CommandObjectController.class)
class CommandObjectControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    @DisplayName("command Object 객체 모델 바인딩 테스트")
    void test1() throws Exception {
        mockMvc.perform(post("/api/post")
            .param("number","1")
            .param("name","jayjun")
            .param("age","35")
            .param("address","판교")
            .param("content","테스트")
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("command inner class 객체 모델 바인딩 테스트1")
    void testInnerClass1() throws Exception {
        mockMvc.perform(post("/api/postinnerclass1")
                .param("number","1")
                .param("name","inner 1")
                .param("content","inner class 테스트")
        ).andExpect(status().isOk());
    }

    @Test
    @DisplayName("command inner class 객체 모델 바인딩 테스트2")
    void testInnerClass2() throws Exception {
        mockMvc.perform(post("/api/postinnerclass2")
                .param("number","2")
                .param("id","inner 2")
                .param("message","inner class 테스트2")
        ).andExpect(status().isOk());
    }


    @Test
    @DisplayName("command inner class 객체 모델 바인딩 테스트3")
    void testInnerClass3() throws Exception {
        mockMvc.perform(post("/api/postinnerclass3")
                .param("number","3")
                .param("id","inner 3")
                .param("name","inner name 3")
                .param("address","inner class 테스트3")
        ).andExpect(status().isOk());
    }


    @Test
    @DisplayName("command inner class 객체 모델 바인딩 테스트4")
    void testInnerClass4() throws Exception {
        mockMvc.perform(post("/api/postinnerclass4")
                .param("number","4")
                .param("id","inner 4")
                .param("name","inner name 4")
                .param("address","inner class 테스트4")
        ).andExpect(status().isOk());
    }

    @Test
    void builder(){
        final var build = CommandDto.builder()
                .number(1)
                .name("test")
                .age(11)
                .address("address")
                .content("content")
                .build();

        System.out.println(build);

        CommandDto commandDto = new CommandDto(2,"test2",22,"address2","content2");

//        CommandDto commandDto2 = new CommandDto(3,"test3",33,"address3");

        System.out.println(commandDto);
//        System.out.println(commandDto2);
    }


}