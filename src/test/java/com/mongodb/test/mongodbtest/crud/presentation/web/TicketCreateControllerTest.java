package com.mongodb.test.mongodbtest.crud.presentation.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@AutoConfigureDataMongo
//@WebMvcTest(TicketCreateController.class)
@SpringBootTest
@AutoConfigureMockMvc
class TicketCreateControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("mongoTemplate 사용해서 조회 하기")
    void test1() throws Exception {
        mockMvc.perform(
                   get("/api/v1/mongoTemplateFindById")
                )
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("mongoOperations 사용해서 조회 하기")
    void test2() throws Exception {
        mockMvc.perform(
                get("/api/v1/customFindById")
        )
                .andExpect(status().isOk());
    }


    @Test
    @DisplayName("mongofindOne 사용해서 조회 하기")
    void test3() throws Exception {
        mockMvc.perform(
                get("/api/v1/findById")
        )
                .andExpect(status().isOk());
    }

}