package com.mongodb.test.mongodbtest.customuser.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface UserRepository extends MongoRepository<User,String>, QuerydslPredicateExecutor<User>, UserRepositoryCustom<User> {
    List<User> findBySeq(Long seq);


}
