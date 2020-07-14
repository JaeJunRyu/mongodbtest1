package com.mongodb.test.mongodbtest.customuser.domain;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String>, CustomUserRepository {
//public interface UserRepository extends MongoRepository<CustomUser,String> {

    List<User> findBySeq(Long seq);


}
