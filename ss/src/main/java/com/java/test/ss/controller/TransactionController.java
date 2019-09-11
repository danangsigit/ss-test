package com.java.test.ss.controller;

import com.java.test.ss.constant.ServicePath;
import com.java.test.ss.model.dto.request.TransactionRequest;
import com.java.test.ss.model.dto.response.SummaryResponse;
import com.java.test.ss.model.dto.response.TransactionResponse;
import com.java.test.ss.model.entity.Transaction;
import com.java.test.ss.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = ServicePath.TRANSACTION_SERVICE)
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @ApiOperation(value = "Create Transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = ServicePath.TRANSACTION_SERVICE, method = RequestMethod.POST)
    public ResponseEntity<TransactionResponse> createTransaction(@RequestBody TransactionRequest request){
        TransactionResponse result = transactionService.saveTransaction(request);
        return new ResponseEntity<TransactionResponse>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "Update Transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = ServicePath.TRANSACTION_SERVICE + ServicePath.TRANSACTION + ServicePath.TRANSACTION_ID, method = RequestMethod.PUT)
    public ResponseEntity<TransactionResponse> updateTransaction(@RequestBody TransactionRequest request, @PathVariable(value = "transaction_id") Long parentId){
        TransactionResponse result = transactionService.putTransaction(request, parentId);
        return new ResponseEntity<TransactionResponse>(result, HttpStatus.OK);
    }

//    @ApiOperation(value = "GET ONE Transaction", produces = MediaType.APPLICATION_JSON_VALUE)
//    @RequestMapping(value = ServicePath.TRANSACTION_SERVICE + ServicePath.TRANSACTION + ServicePath.TRANSACTION_ID, method = RequestMethod.GET)
//    public ResponseEntity<TransactionResponse> getOneTransaction(@PathVariable(value = "transaction_id") Long parentId){
//        TransactionResponse result = transactionService.getOneTransaction(parentId);
//        return new ResponseEntity<TransactionResponse>(result, HttpStatus.OK);
//    }

    @ApiOperation(value = "GET List Transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = ServicePath.TRANSACTION_SERVICE + ServicePath.TRANSACTION_TYPES + ServicePath.TRANSACTION_TYPE, method = RequestMethod.GET)
    public ResponseEntity<List<TransactionResponse>> getListTransaction(@PathVariable(value = "type") String type){
        List<TransactionResponse> result = transactionService.getListTransaction(type);
        return new ResponseEntity<List<TransactionResponse>>(result, HttpStatus.OK);
    }

    @ApiOperation(value = "GET SUM Transaction", produces = MediaType.APPLICATION_JSON_VALUE)
    @RequestMapping(value = ServicePath.TRANSACTION_SERVICE + ServicePath.TRANSACTION + ServicePath.TRANSACTION_ID, method = RequestMethod.GET)
    public ResponseEntity<SummaryResponse> getSumTransaction(@PathVariable(value = "transaction_id") Long parentId){
        SummaryResponse rest = transactionService.getSumTransaction(parentId);
        return new ResponseEntity<SummaryResponse>(rest, HttpStatus.OK);
    }


}
