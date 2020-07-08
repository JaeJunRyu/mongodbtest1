package com.mongodb.test.mongodbtest.crud.service.ticket;

import com.mongodb.test.mongodbtest.crud.presentation.web.dto.TicketProductSaveRequestDto;

public interface TicketProductService {

    String ticketProductInsert(TicketProductSaveRequestDto requestDto);

    String customFindById(String id);

}
