package com.mongodb.test.mongodbtest.mongobook.domain;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserColRepository extends MongoRepository<UserCol,String> {

    @Query("{ '$or' : [ { 'height' : { '$gt' : 180 } }, { 'height' : { '$lt' : 10 } } ] }")
    List<UserCol> findByHeightList();

    @Query(value = "{ '$or' : [ { 'height' : { '$gt' : ?0 } }, { 'height' : { '$lt' : ?1 } } ] }")
    List<UserCol> findByHeightList2(Integer gt, Integer lt);

    @Query(value = "{ '$and' : [ { 'height' : { '$gt' : ?0 } }, { 'height' : { '$lt' : ?1 } } ] }")
    List<UserCol> findByHeightGtLt(Integer gt, Integer lt);

}
