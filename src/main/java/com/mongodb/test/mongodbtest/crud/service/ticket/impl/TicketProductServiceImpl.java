package com.mongodb.test.mongodbtest.crud.service.ticket.impl;

import com.mongodb.test.mongodbtest.crud.domain.ticket.TicketProduct;
import com.mongodb.test.mongodbtest.crud.domain.ticket.TicketProductCustomRepository;
import com.mongodb.test.mongodbtest.crud.domain.ticket.TicketProductRepository;
import com.mongodb.test.mongodbtest.crud.presentation.web.dto.TicketProductSaveRequestDto;
import com.mongodb.test.mongodbtest.crud.service.sequence.NextSequence;
import com.mongodb.test.mongodbtest.crud.service.ticket.TicketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
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
    public String customFindById(String id) {
        return ticketProductCustomRepository.customFindById(id);
    }



}
