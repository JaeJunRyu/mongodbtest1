package com.mongodb.test.mongodbtest.customuser.domain;

import com.mongodb.test.mongodbtest.customuser.presentation.dto.CustomDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class UserRepositoryImpl implements CustomUserRepository {

    private final MongoOperations mongoOperations;

    //    @Override
//    public List<CustomUser> customMethod() {
//        mongoOperations.upsert();
//        mongoOperations.save();
//        mongoOperations.findById();
//        mongoOperations.findAll();
//        mongoOperations.insert();
//        mongoOperations.aggregate();
//        mongoOperations.aggregateStream();
//        return null;
//    }

    @Override
    public Long customInsert(CustomDto.CustomRequestDto customRequestDto) {
        return mongoOperations.insert(customRequestDto.toEntity(1L)).getSeq();
    }

}
