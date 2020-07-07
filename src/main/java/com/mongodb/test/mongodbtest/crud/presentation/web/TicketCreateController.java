package com.mongodb.test.mongodbtest.crud.presentation.web;

import com.mongodb.test.mongodbtest.crud.presentation.web.dto.TicketProductSaveRequestDto;
import com.mongodb.test.mongodbtest.crud.service.ticket.TicketProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@RestController
@RequestMapping(value = "/api/v1")
public class TicketCreateController {

    private final TicketProductService ticketProductService;


    @PostMapping(value = "/ticketProductSave")
    public String ticketProductSave(TicketProductSaveRequestDto ticketProductSaveRequestDto,
            HttpServletRequest request ) {

        String id = ticketProductService.ticketProductInsert(ticketProductSaveRequestDto);

        return id;
    }



}
