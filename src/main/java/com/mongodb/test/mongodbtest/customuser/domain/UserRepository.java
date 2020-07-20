package com.mongodb.test.mongodbtest.customuser.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;

import java.util.List;


public interface UserRepository extends MongoRepository<User,String>, QuerydslPredicateExecutor<User>,UserRepositoryCustom {
    List<User> findBySeq(Long seq);
}
