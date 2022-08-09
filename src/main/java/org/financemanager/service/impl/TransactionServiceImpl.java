package org.financemanager.service.impl;

import org.financemanager.Exception.NoSuchCategoryException;
import org.financemanager.Exception.NoSuchTransactionExeption;
import org.financemanager.entity.Category;
import org.financemanager.entity.Transaction;
import org.financemanager.repository.TransactionRepo;
import org.financemanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepo transactionRepo;

    @Override
    public List<Transaction> findAll() {
        return transactionRepo.findAll();
    }

    @Override
    public Optional<Transaction> findById(Long id) {
        try {
            return transactionRepo.findById(id);
        } catch (NoSuchTransactionExeption e) {
            throw new NoSuchTransactionExeption();
        }
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
        return transactionRepo.save(transaction);
    }

    @Override
    public Transaction update(Long id, Transaction transaction) {
        try {
            Optional<Transaction> t = findById(id);
            if (t.isPresent()) {
                t.get().setCategory(transaction.getCategory());
                t.get().setOperationType(transaction.getOperationType());
                t.get().setSum(transaction.getSum());
                t.get().setDate(transaction.getDate());
                t.get().setDescription(transaction.getDescription());
            }
            return save(t.get());
        } catch (NoSuchTransactionExeption e) {
            throw new NoSuchTransactionExeption();
        }
    }

    @Override
    public void delete(Long id) {
        transactionRepo.deleteById(id);
    }
}
