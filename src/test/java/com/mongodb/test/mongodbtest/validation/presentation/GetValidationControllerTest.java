package com.mongodb.test.mongodbtest.validation.presentation;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(GetValidationController.class)

//@SpringBootTest
//@AutoConfigureMockMvc
class GetValidationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("get validation test")
    void getTest1() throws Exception {
        //given


        //when
        final var resultActions = mockMvc.perform(
                get("/api/v1/validation/get")
        ).andExpect(status().isOk());

        //then

        System.out.println("aaa");
    }




}