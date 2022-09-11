package org.financemanager.service;

import org.financemanager.entity.Category;
import org.financemanager.entity.Transaction;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public interface TransactionService {
    List<Transaction> findAll();

    List<Transaction> findAllByDateBetween(Date dateStart, Date dateEnd);

    List<Transaction> findAllByDateBetweenAndOperationType(Date dateStart, Date dateEnd, String operationType);

    List<Transaction> findAllByDateBetweenAndOperationTypeAndCategory(Date dateStart, Date dateEnd, String operationType, Category category);

    Optional<Transaction> findById(Long id);

    Transaction save(Transaction transaction);

    Transaction update(Long id, Transaction transaction);

    void delete(Long id);
}