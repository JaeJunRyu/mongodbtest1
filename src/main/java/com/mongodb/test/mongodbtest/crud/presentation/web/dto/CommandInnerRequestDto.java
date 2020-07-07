package com.mongodb.test.mongodbtest.crud.presentation.web.dto;

import lombok.*;

public class CommandInnerRequestDto {

    @Getter
    @AllArgsConstructor
    @Builder
    @ToString
    public static class CommandInnerClass1 {
        private Integer number;
        private String name;
        private String content;

        public CommandInnerClass1 toDto1(){
            return CommandInnerClass1.builder()
                    .number(1)
                    .name("name")
                    .content("content")
                    .build();
        }

        public CommandInnerClass1 toDto2() {
            return new CommandInnerClass1(1,"name","content");
        }

//        public EntityClass toEntity(){
//
//        }

    }

    @Getter
    @Setter
    @ToString
    public static class CommandInnerClass2 {
        private Integer number;
        private String id;
        private String message;
    }

    @Getter
    @AllArgsConstructor
    @ToString
    public static class CommandInnerClass3 {
        private Integer number;
        private String id;
        private String name;
        private String address;
    }


    @Getter
    @AllArgsConstructor
    @ToString
    public class CommandInnerClass4 {
        private Integer number;
        private String id;
        private String name;
        private String address;
    }

}
