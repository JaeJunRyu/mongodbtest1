package com.mongodb.test.mongodbtest.customuser.presentation;

import com.mongodb.test.mongodbtest.customuser.presentation.dto.CustomDto;
import com.mongodb.test.mongodbtest.customuser.service.CustomUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1/custom")
public class CustomUserController {

//    private final CustomUserService customUserService;


//    @PostMapping(value = "insert")
//    public String insert(CustomDto.CustomRequestDto customRequestDto ) {
//
//        customUserService.insert(customRequestDto);
//        System.out.println(customRequestDto.toString());
//
//        return "";
//    }




}
