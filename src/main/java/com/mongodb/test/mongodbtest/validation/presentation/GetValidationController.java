package com.mongodb.test.mongodbtest.validation.presentation;

import com.mongodb.test.mongodbtest.validation.presentation.dto.GetValidationRequestDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/validation")
public class GetValidationController {

    @GetMapping(value = "get")
    public ResponseEntity<?> getFormInputDateValidation(GetValidationRequestDto dto){

        return new ResponseEntity<>("", HttpStatus.OK);
    }


    /*
        get 입력 값 검증
        post 입력 값 검증
        json 입력 값 검증

     */


}
