package com.mongodb.test.mongodbtest.crud.service.ticket.impl;

import com.mongodb.test.mongodbtest.crud.domain.ticket.TicketProduct;
import com.mongodb.test.mongodbtest.crud.domain.ticket.TicketProductCustomRepository;
import com.mongodb.test.mongodbtest.crud.domain.ticket.TicketProductRepository;
import com.mongodb.test.mongodbtest.crud.presentation.web.dto.TicketProductSaveRequestDto;
import com.mongodb.test.mongodbtest.crud.service.sequence.NextSequence;
import com.mongodb.test.mongodbtest.crud.service.ticket.TicketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class TicketProductServiceImpl implements TicketProductService {

    private final TicketProductRepository ticketProductRepository;

    private final TicketProductCustomRepository ticketProductCustomRepository;

    private final NextSequence nextSequence;

    @Override
    public String ticketProductInsert(TicketProductSaveRequestDto requestDto) {
        final var nextSequence = this.nextSequence.getNextSequence(TicketProduct.SEQUENCE_NAME);

        return ticketProductRepository.save(requestDto.toEntity(nextSequence)).getId();
    }

    @Override
    public String findById(String id) {
//        Query query = new Query();
//        query.addCriteria(Criteria.where("seq").is(15));
        TicketProduct ticketProduct = new TicketProduct(15L);

//        ExampleMatcher exampleMatcher = ExampleMatcher.matching()
//                .withIgnoreCase();

        final TicketProduct ticketProduct1 = ticketProductRepository.findOne(Example.of(ticketProduct)).orElse(null);
        System.out.println(ticketProduct1.getId());
        return ticketProduct1.getId();
    }

    @Override
    public String customFindById(String id) {
        return ticketProductCustomRepository.customFindById(id);
    }

    @Override
    public String mongoTemplateFindById(String id) {
        return ticketProductCustomRepository.mongoTemplateFindById(id);
    }
}
