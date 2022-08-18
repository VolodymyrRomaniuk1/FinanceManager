package org.financemanager.service;

import org.financemanager.entity.Transaction;

import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> findAll();

    Optional<Transaction> findById(Long id);

    Transaction save(Transaction transaction);

    Transaction update(Long id, Transaction transaction);

    void delete(Long id);
}