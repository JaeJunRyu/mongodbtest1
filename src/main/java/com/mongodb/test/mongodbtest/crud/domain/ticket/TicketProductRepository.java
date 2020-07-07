package com.mongodb.test.mongodbtest.crud.domain.ticket;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TicketProductRepository extends MongoRepository<TicketProduct, String> {




}
