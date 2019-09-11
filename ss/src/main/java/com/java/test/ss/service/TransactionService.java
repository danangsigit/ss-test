package com.java.test.ss.service;

import com.java.test.ss.model.dto.request.TransactionRequest;
import com.java.test.ss.model.dto.response.SummaryResponse;
import com.java.test.ss.model.dto.response.TransactionResponse;
import com.java.test.ss.model.entity.Transaction;

import java.util.List;
import java.util.Map;

public interface TransactionService {
    TransactionResponse saveTransaction(TransactionRequest request);
    TransactionResponse putTransaction(TransactionRequest request, Long parentId);
    TransactionResponse getOneTransaction(Long parentId);
    List<TransactionResponse> getListTransaction(String type);
    SummaryResponse  getSumTransaction(Long parentId);
    Transaction findTransaction(Long parentId);
}
