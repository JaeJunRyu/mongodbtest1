package com.mongodb.test.mongodbtest.customuser.domain;

import com.mongodb.test.mongodbtest.customuser.presentation.dto.CustomDto;

import java.util.List;

public interface UserRepositoryCustom {

//    List<CustomUser> customMethod();

    Long customInsert(CustomDto.CustomRequestDto customRequestDto);





}
