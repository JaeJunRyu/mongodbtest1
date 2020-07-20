package com.mongodb.test.mongodbtest.customuser.infrastructure.mongo;

import com.mongodb.test.mongodbtest.customuser.domain.UserRepositoryCustom;
import com.mongodb.test.mongodbtest.customuser.presentation.dto.CustomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements UserRepositoryCustom {

    private final MongoOperations mongoOperations;

    @Override
    public Long customInsert(CustomDto.CustomRequestDto customRequestDto) {
        return mongoOperations.insert(customRequestDto.toEntity(1L)).getSeq();
    }

}
