package com.java.test.ss.model.mapper;

import com.java.test.ss.model.dto.response.TransactionResponse;
import com.java.test.ss.model.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface TransactionMapper {
    TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    TransactionResponse toTransaction(Transaction transaction);

    List<TransactionResponse> toTransactions(List<Transaction> transactions);
}
