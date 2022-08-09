package org.financemanager.repository;

import org.financemanager.entity.Category;
import org.financemanager.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findAll();

    Optional<Transaction> findById(Long id);

    //List<Transaction> findTransactionsByCategory(Category category);
}
