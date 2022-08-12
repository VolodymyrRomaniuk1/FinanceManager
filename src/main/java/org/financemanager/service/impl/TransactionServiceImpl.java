package org.financemanager.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.exception.NoSuchTransactionExeption;
import org.financemanager.entity.Transaction;
import org.financemanager.repository.TransactionRepo;
import org.financemanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
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
    public Optional<Transaction> findById(Long id) {
        logger.info("Executing transaction id " + id +" findById");
        Optional<Transaction> t = transactionRepo.findById(id);
            if (t.isEmpty()) {
                logger.info("Transaction id " + id + " not found");
                throw new NoSuchTransactionExeption();
            }
            logger.info("Transaction id " + id + " found");
            return t;
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
        }catch (NoSuchTransactionExeption e){
            logger.info("Transaction save failed");
            throw new NoSuchTransactionExeption();
        }

    }

    @Override
    public Transaction update(Long id, Transaction transaction) {
        logger.info("Executing transaction id " + id + " update");
        try {
            Optional<Transaction> t = findById(id);
            if (t.isPresent()) {
                logger.info("Transaction id " + id + " exists");
                t.get().setCategory(transaction.getCategory());
                t.get().setOperationType(transaction.getOperationType());
                t.get().setSum(transaction.getSum());
                t.get().setDate(transaction.getDate());
                t.get().setDescription(transaction.getDescription());
            }
            return save(t.get());
        } catch (NoSuchTransactionExeption e) {
            logger.info("Transaction id " + id + " update failed");
            throw new NoSuchTransactionExeption();
        }
    }

    @Override
    public void delete(Long id) {
        logger.info("Executing transaction id " + id + " delete");
        try {
            transactionRepo.deleteById(id);
        } catch (NoSuchTransactionExeption e) {
            logger.info("Transaction id " + id + " deletion failed");
            throw new NoSuchTransactionExeption();
        }
    }
}