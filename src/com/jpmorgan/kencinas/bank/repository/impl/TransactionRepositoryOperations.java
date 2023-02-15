package com.jpmorgan.kencinas.bank.repository.impl;

import java.util.ArrayList;
import java.util.List;

import com.jpmorgan.kencinas.bank.model.Transaction;
import com.jpmorgan.kencinas.bank.repository.TransactionRepository;

public class TransactionRepositoryOperations implements TransactionRepository {
    private static List<Transaction> transactionHistory = new ArrayList<>();

    @Override
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

    @Override
    public void insertTransaction(final Transaction transaction) {
        transactionHistory.add(transaction);
    }

    @Override
    public void clearTransactionHistory() {
        transactionHistory.clear();
    }
}
