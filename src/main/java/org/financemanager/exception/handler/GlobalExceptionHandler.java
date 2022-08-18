package org.financemanager.exception.handler;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.financemanager.exception.CategoryAlreadyExistsException;
import org.financemanager.exception.NoSuchCategoryException;
import org.financemanager.exception.NoSuchTransactionException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    public static final Logger logger = LogManager.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(NoSuchCategoryException.class)
    public ResponseEntity<String> noSuchCategoryExceptionHandler(){
        logger.error("NoSuchCategoryException thrown");
        return new ResponseEntity<>("No such category", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchTransactionException.class)
    public ResponseEntity<String> noSuchTransactionExceptionHandler(){
        logger.error("NoSuchTransactionException thrown");
        return new ResponseEntity<>("No such transaction", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(CategoryAlreadyExistsException.class)
    public ResponseEntity<String> categoryAlreadyExistsException(){
        logger.error("CategoryAlreadyExistsException thrown");
        return new ResponseEntity<>("Category already exists", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> uncaughtExceptionHandler(Exception e){
        logger.error("Thrown: " + e);
        return new ResponseEntity<>("Error occured", HttpStatus.NOT_FOUND);
    }
}