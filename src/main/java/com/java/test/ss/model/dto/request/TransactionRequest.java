package com.java.test.ss.model.dto.request;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class TransactionRequest {
    @JsonIgnore
    private Long parentId;

    private String type;
    private double amount;
}

