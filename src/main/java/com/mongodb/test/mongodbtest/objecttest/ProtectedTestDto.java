package com.mongodb.test.mongodbtest.objecttest;

import lombok.Getter;

@Getter
public class ProtectedTestDto {

    protected ProtectedTestDto() {
    }

    private String name;

    private String content;



}
