package com.mongodb.test.mongodbtest.customuser.domain;

import com.mongodb.test.mongodbtest.customuser.presentation.dto.CustomDto;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface UserRepositoryCustom<T> {

//    List<CustomUser> customMethod();

    Long customInsert(CustomDto.CustomRequestDto customRequestDto);






}
