package com.custacc.controller;

import com.custacc.dto.ResponseDto;
import com.custacc.service.ActionFacade;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping
public class RestController {

    final ActionFacade actionFacade;

    public RestController(ActionFacade actionFacade) {
        this.actionFacade = actionFacade;
    }

    @PutMapping(path = {"/initialize"})
    public ResponseEntity<ResponseDto<?>> initializeData() {
        ResponseDto<?> response = ResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .body(actionFacade.initializeData()).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PostMapping(path = {"/customer/{customerID}/initialCredit/{initialCredit}"})
    public ResponseEntity<ResponseDto<?>> createAccount(@PathVariable("customerID") Long customerID, @PathVariable("initialCredit") Long balance) throws Exception {
        ResponseDto<?> response = ResponseDto.builder()
                .status(HttpStatus.CREATED.toString())
                .body(actionFacade.createAccount(customerID, balance)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = {"/customer/{customerID}"})
    public ResponseEntity<ResponseDto<?>> getCustomerDetails(@PathVariable("customerID") Long customerID) throws Exception {
        ResponseDto<?> response = ResponseDto.builder()
                .status(HttpStatus.OK.toString())
                .body(actionFacade.getCustomerDetails(customerID)).build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
