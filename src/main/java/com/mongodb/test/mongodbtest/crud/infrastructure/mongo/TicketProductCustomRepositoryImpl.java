package com.mongodb.test.mongodbtest.crud.infrastructure.mongo;

import com.mongodb.test.mongodbtest.crud.domain.ticket.TicketProduct;
import com.mongodb.test.mongodbtest.crud.domain.ticket.TicketProductCustomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class TicketProductCustomRepositoryImpl implements TicketProductCustomRepository {

    private final MongoOperations mongoOperations;

    @Override
    public String customFindById(String id) {
        String testid = "5f02aff2b5531826181598c2";
        Query query = new Query();
        query.addCriteria(Criteria.where("seq").is(15));
        final var byId = mongoOperations.findOne(query, TicketProduct.class).getId();

        System.out.println(byId);

        return byId;
    }


}
