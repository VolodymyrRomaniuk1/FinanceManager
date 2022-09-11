package org.financemanager.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.entity.Category;
import org.financemanager.exception.NoSuchTransactionException;
import org.financemanager.entity.Transaction;
import org.financemanager.repository.TransactionRepo;
import org.financemanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    public static final Logger logger = LogManager.getLogger(TransactionServiceImpl.class);

    private TransactionRepo transactionRepo;

    @Autowired
    public TransactionServiceImpl(TransactionRepo transactionRepo) {
        this.transactionRepo = transactionRepo;
    }

    @Override
    public List<Transaction> findAll() {
        logger.info("Executing transaction findAll");
        return transactionRepo.findAll();
    }

    @Override
    public List<Transaction> findAllByDateBetween(Date dateStart, Date dateEnd){
        logger.info("Executing transaction findAllByDateBetween '" + dateStart + "' and '" + dateEnd + "'");
        return transactionRepo.findAllByDateBetween(dateStart, dateEnd);
    }

    @Override
    public List<Transaction> findAllByDateBetweenAndOperationType(Date dateStart, Date dateEnd, String operationType){
        logger.info("Executing transaction findAllByDateBetween '" + dateStart + "' and '" + dateEnd + "' with operation type '" + operationType + "'");
        return transactionRepo.findAllByDateBetweenAndOperationType(dateStart, dateEnd, operationType);
    }

    @Override
    public List<Transaction> findAllByDateBetweenAndOperationTypeAndCategory(Date dateStart, Date dateEnd, String operationType, Category category){
        logger.info("Executing transaction findAllByDateBetween '" + dateStart + "' and '" + dateEnd + "' with operation type '" + operationType + "' with category " + category);
        return transactionRepo.findAllByDateBetweenAndOperationTypeAndCategory(dateStart, dateEnd, operationType, category);
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        logger.info("Executing transaction id " + id +" findById");
        Optional<Transaction> transaction = transactionRepo.findById(id);
            if (transaction.isEmpty()) {
                logger.error("Transaction id " + id + " not found");
                throw new NoSuchTransactionException("No such transaction");
            }
            logger.info("Transaction id " + id + " found");
            return transaction;
    }

//    @Override
//    //@Query(value = "select * from transactions where transactions.category_id = ?1", nativeQuery = true)
//        public List<Transaction> findTransactionsByCategory(Long id){
//        return transactionRepo.findTransactionsByCategory();
//    }
////    public List<Transaction> findTransactionsByCategory(Long id){
////        List<Transaction> tran = findAll();
////        tran.removeIf(t -> !Objects.equals(t.getCategory().getId(), id));
////        return tran;
////    }

    @Override
    public Transaction save(Transaction transaction) {
        logger.info("Executing transaction save");
        try {
            return transactionRepo.save(transaction);
        }catch (NoSuchTransactionException e){
            logger.error("Transaction save failed");
            throw new NoSuchTransactionException("No such transaction");
        }

    }

    @Override
    public Transaction update(Long id, Transaction transaction) {
        logger.info("Executing transaction id " + id + " update");
        try {
            Optional<Transaction> newTransaction = findById(id);
            if (newTransaction.isPresent()) {
                logger.info("Transaction id " + id + " exists");
                newTransaction.get().setCategory(transaction.getCategory());
                newTransaction.get().setOperationType(transaction.getOperationType());
                newTransaction.get().setSum(transaction.getSum());
                newTransaction.get().setDate(transaction.getDate());
                newTransaction.get().setDescription(transaction.getDescription());
            }
            return save(newTransaction.get());
        } catch (NoSuchTransactionException e) {
            logger.error("Transaction id " + id + " update failed");
            throw new NoSuchTransactionException("No such transaction");
        }
    }

    @Override
    public void delete(Long id) {
        logger.info("Executing transaction id " + id + " delete");
        try {
            transactionRepo.deleteById(id);
        } catch (NoSuchTransactionException e) {
            logger.error("Transaction id " + id + " deletion failed");
            throw new NoSuchTransactionException("No such transaction");
        }
    }
}