package com.mongodb.test.mongodbtest;

import com.mongodb.test.mongodbtest.customuser.domain.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
//@SpringBootTest
@DataMongoTest
//@EnableMongoRepositories(basePackageClasses = {UserRepositoryImpl.class})
//@AutoConfigureDataMongo
//@DataMongoTest(excludeAutoConfiguration = EmbeddedMongoAutoConfiguration.class)
public class MongoTest {

//    @Autowired
//    private UserRepository userRepository;

    @Autowired
    private MongoOperations mongoOperations;

    @Test
    void test1() {
        final User mongo_test = User.builder()
                .name("mongo test")
                .build();

        mongoOperations.insert(mongo_test);

        Query query = new Query();
        query.addCriteria(Criteria.where("name").is("mongo test"));

        final User one = mongoOperations.findOne(query, User.class);

        System.out.println(one.getName());

    }



}
