package org.financemanager.repository;

import org.financemanager.entity.Category;
import org.financemanager.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    @Query("select t from Transaction t order by t.id asc")
    List<Transaction> findAll();

    List<Transaction> findAllByDateBetween(Date dateStart, Date dateEnd);

    List<Transaction> findAllByDateBetweenAndOperationType(Date dateStart, Date dateEnd, String operationType);
    //List<Transaction> findTransactionsByCategory(Category category);
}
