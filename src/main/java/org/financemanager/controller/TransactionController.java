package org.financemanager.controller;

import org.financemanager.entity.Transaction;
import org.financemanager.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepo  transactionRepo;

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactionsList(Model model){
        List<Transaction> transactions = transactionRepo.findAll();
        if(transactions.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        model.addAttribute("listTransactions", transactions);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<Optional<Transaction>> getById(@PathVariable("id") Long id){
        Optional<Transaction> transaction;
        transaction = transactionRepo.findById(id);
        if(transaction.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transaction, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@ModelAttribute("transaction") Transaction transaction) {
        if(transaction == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transactionRepo.save(transaction);
        return new ResponseEntity<>(transaction, HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updateTransaction(@PathVariable int id, @RequestBody Transaction transaction){
        if(transaction == null){
            return new ResponseEntity<>("Error: Transaction not found", HttpStatus.NOT_FOUND);
        }
        transactionRepo.save(transaction);
        return new ResponseEntity<>("Transaction successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long id){
        transactionRepo.deleteById(id);
        return new ResponseEntity<>("Category deleted.", HttpStatus.OK);
    }
}
