package com.mongodb.test.mongodbtest.crud.presentation.web;

import com.mongodb.test.mongodbtest.crud.presentation.web.dto.CommandDto;
import com.mongodb.test.mongodbtest.crud.presentation.web.dto.CommandInnerRequestDto;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//@Slf4j
@RestController
@RequestMapping(value = "/api")
public class CommandObjectController {

    @PostMapping(value = "/post")
    public String commandObjectTest( CommandDto commandDto ) {
        System.out.println(commandDto.toString());
        return "";
    }

    @PostMapping(value = "/postinnerclass1")
    public String commandObjectInnerClassTest1( CommandInnerRequestDto.CommandInnerClass1 commandInnerClass1 ) {
        System.out.println(commandInnerClass1.toString());
        return "";
    }

    @PostMapping(value = "/postinnerclass2")
    public String commandObjectInnerClassTest2( CommandInnerRequestDto.CommandInnerClass2 commandInnerClass2 ) {
        System.out.println(commandInnerClass2.toString());
        return "";
    }

    @PostMapping(value = "/postinnerclass3")
    public String commandObjectInnerClassTest3( CommandInnerRequestDto.CommandInnerClass3 commandInnerClass3 ) {
        System.out.println(commandInnerClass3.toString());
        return "";
    }

    @PostMapping(value = "/postinnerclass4")
    public String commandObjectInnerClassTest4( CommandInnerRequestDto.CommandInnerClass4 commandInnerClass4 ) {

        System.out.println(commandInnerClass4.toString());
        return "";
    }

}
