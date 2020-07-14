package com.mongodb.test.mongodbtest.crud.service.ticket;

import com.mongodb.test.mongodbtest.crud.presentation.web.dto.TicketProductSaveRequestDto;

public interface TicketProductService {

    String ticketProductInsert(TicketProductSaveRequestDto requestDto);

    String findById(String id);

    String customFindById(String id);

    String mongoTemplateFindById(String id);

}
