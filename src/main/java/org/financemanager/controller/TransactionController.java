package org.financemanager.controller;

import org.financemanager.entity.Transaction;
import org.financemanager.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactionsList(Model model){
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("listTransactions", transactions);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("{id:[\\d]+}")
    public ResponseEntity<Optional<Transaction>> getById(@PathVariable("id") Long id){
        return new ResponseEntity<>(transactionService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> createTransaction(@ModelAttribute("transaction") @Valid Transaction transaction, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.CREATED);
    }

    @PutMapping("{id:[\\d]+}")
    public ResponseEntity<String> updateTransaction(@PathVariable Long id, @RequestBody @Valid Transaction transaction, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transactionService.update(id, transaction);
        return new ResponseEntity<>("Transaction successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping("{id:[\\d]+}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long id){
        transactionService.delete(id);
        return new ResponseEntity<>("Transaction deleted.", HttpStatus.OK);
    }
}
