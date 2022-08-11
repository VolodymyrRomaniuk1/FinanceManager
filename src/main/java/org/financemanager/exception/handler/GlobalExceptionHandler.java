package org.financemanager.exception.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.exception.CategoryAlreadyExistsException;
import org.financemanager.exception.NoSuchCategoryException;
import org.financemanager.exception.NoSuchTransactionExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger log = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchCategoryException.class)
    public ResponseEntity<String> NoSuchCategoryExceptionHandler(){
        log.info("NoSuchCategoryException");
        return new ResponseEntity<>("No such category", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchTransactionExeption.class)
    public ResponseEntity<String> NoSuchTransactionExceptionHandler(){
        log.info("NoSuchTransactionException");
        return new ResponseEntity<>("No such transaction", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<String> CategoryAlreadyExistsException(){
        log.info("CategoryAlreadyExistsException");
        return new ResponseEntity<>("Category already exists", HttpStatus.NOT_FOUND);
    }
}