package org.financemanager.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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

    public static final Logger logger = LogManager.getLogger(TransactionController.class);

    private TransactionService transactionService;

    @Autowired
    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> findAll(Model model){
        logger.info("Getting transactions list");
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("transactionsList", transactions);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    @GetMapping("{id:[\\d]+}")
    public ResponseEntity<Optional<Transaction>> getById(@PathVariable("id") Long id){
        logger.info("Getting transaction by id " + id);
        return new ResponseEntity<>(transactionService.findById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Transaction> saveTransaction(@ModelAttribute("transaction") @Valid Transaction transaction, BindingResult bindingResult) {
        logger.info("Creating new transaction");
        if(bindingResult.hasErrors()){
            logger.error("Provided transaction has errors");
            logger.info(bindingResult.getAllErrors());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(transactionService.save(transaction), HttpStatus.CREATED);
    }

    @PutMapping("{id:[\\d]+}")
    public ResponseEntity<String> updateTransaction(@PathVariable Long id, @RequestBody @Valid Transaction transaction, BindingResult bindingResult){
        logger.info("Updating transaction id " + id);
        if(bindingResult.hasErrors()){
            logger.error("Provided transaction has errors");
            logger.info(bindingResult.getAllErrors());
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        transactionService.update(id, transaction);
        logger.info("Transaction id " + id + " successfully updated");
        return new ResponseEntity<>("Transaction successfully updated.", HttpStatus.OK);
    }

    @DeleteMapping("{id:[\\d]+}")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") Long id){
        logger.info("Deleting transaction id " + id);
        transactionService.delete(id);
        logger.info("Transaction id " + id + " deleted");
        return new ResponseEntity<>("Transaction deleted.", HttpStatus.OK);
    }
}
