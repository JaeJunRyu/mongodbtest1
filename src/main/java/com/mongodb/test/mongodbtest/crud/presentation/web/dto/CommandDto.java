package com.mongodb.test.mongodbtest.crud.presentation.web.dto;

import lombok.*;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@Builder
public class CommandDto {

    private Integer number;

    private String name;

    private Integer age;

    private String address;

    private String content;

}
