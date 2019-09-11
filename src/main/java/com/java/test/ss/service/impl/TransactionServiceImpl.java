package com.java.test.ss.service.impl;

import com.java.test.ss.model.dto.request.TransactionRequest;
import com.java.test.ss.model.dto.response.SummaryResponse;
import com.java.test.ss.model.dto.response.TransactionResponse;
import com.java.test.ss.model.entity.Transaction;
import com.java.test.ss.model.mapper.TransactionMapper;
import com.java.test.ss.repository.TransactionRepository;
import com.java.test.ss.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {
    public static final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    public Transaction findTransaction(Long parentId){
        Optional<Transaction> transactionOptional = transactionRepository.findById(parentId);

        if(transactionOptional.isPresent()){
            return transactionOptional.get();
        }
        return null;
    }


    @Override
    public SummaryResponse getSumTransaction(Long parentId) {
        SummaryResponse rest = new SummaryResponse();
        Transaction transaction = findTransaction(parentId);
        rest.setSum(transaction.getAmount());
        return rest;
    }

    @Override
    public List<TransactionResponse> getListTransaction(String type){
        return TransactionMapper.INSTANCE.toTransactions(transactionRepository.findAllByType(type));
    }

    @Override
    public TransactionResponse getOneTransaction(Long parentId){
        if(parentId ==null ){
            return null;
        }
        return TransactionMapper.INSTANCE.toTransaction(findTransaction(parentId));
    }

    @Override
    public TransactionResponse putTransaction(TransactionRequest request, Long parentId){
        Transaction transaction = findTransaction(parentId);
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transactionRepository.saveAndFlush(transaction);
        return TransactionMapper.INSTANCE.toTransaction(findTransaction(parentId));
    }

    @Override
    public TransactionResponse saveTransaction(TransactionRequest request){
        Transaction transaction = new Transaction();
        transaction.setAmount(request.getAmount());
        transaction.setType(request.getType());
        transactionRepository.saveAndFlush(transaction);
        logger.info("hahaha : {}",transaction.getParentId());
        return  TransactionMapper.INSTANCE.toTransaction(transactionRepository.findFirstByParentId(transaction.getParentId()));
    }
}
