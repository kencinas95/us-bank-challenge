package com.company.kencinas.bank.repository;

import java.util.List;

import com.company.kencinas.bank.model.Transaction;

public interface TransactionRepository {
    List<Transaction> getTransactionHistory();

    void insertTransaction(Transaction transaction);

    void clearTransactionHistory(); // Test purpose only

}
