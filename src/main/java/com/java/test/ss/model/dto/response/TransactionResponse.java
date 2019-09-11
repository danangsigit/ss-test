package com.java.test.ss.model.dto.response;

import com.java.test.ss.model.entity.Transaction;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Data
@Getter @Setter
public class TransactionResponse implements Serializable {
    private Long parentId;
    private String type;
    private double amount;
}
