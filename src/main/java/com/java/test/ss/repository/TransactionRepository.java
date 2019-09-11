package com.java.test.ss.repository;

import com.java.test.ss.model.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    Transaction findFirstByParentId(Long parentId);
    List<Transaction> findAllByType(String type);
}

