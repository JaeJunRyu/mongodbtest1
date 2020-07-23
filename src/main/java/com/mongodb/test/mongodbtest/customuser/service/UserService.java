package com.mongodb.test.mongodbtest.customuser.service;

import com.mongodb.test.mongodbtest.customuser.domain.QUser;
import com.mongodb.test.mongodbtest.customuser.domain.User;
import com.mongodb.test.mongodbtest.customuser.domain.UserRepository;
import com.mongodb.test.mongodbtest.customuser.presentation.dto.CustomDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Long insert(CustomDto.CustomRequestDto dto){
        return userRepository.save(dto.toEntity(1L)).getSeq();
    }

    public Long customInsert(CustomDto.CustomRequestDto dto){
        return userRepository.customInsert(dto);
    }


    public void queryDslTest() {

        QUser qUser = new QUser("user");
//        https://www.baeldung.com/queries-in-spring-data-mongodb

        //다음주에 더 살펴보기
        final Iterable<User> datalist = userRepository.findAll(qUser.name.eq("test name5"));

        for (User user : datalist) {
            System.out.println(user);
        }

    }


    public String findById(String id) {
        final var customUser = userRepository.findById(id).orElse(null);
        final var bySeq = userRepository.findBySeq(1L);

        final CustomDto.CustomResponseDto responseDto = new CustomDto.CustomResponseDto(customUser);

        return responseDto.getId();
    }

}
