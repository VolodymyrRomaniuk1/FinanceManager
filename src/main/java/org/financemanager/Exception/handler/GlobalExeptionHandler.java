package org.financemanager.Exception.handler;

import org.financemanager.Exception.NoSuchCategoryException;
import org.financemanager.Exception.NoSuchTransactionExeption;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class GlobalExeptionHandler {

    @ExceptionHandler(NoSuchCategoryException.class)
    public ResponseEntity<String> NoSuchCategoryExceptionHandler(){
        return new ResponseEntity<>("No such category", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(NoSuchTransactionExeption.class)
    public ResponseEntity<String> NoSuchTransactionExeptionHandler(){
        return new ResponseEntity<>("No such transaction", HttpStatus.NOT_FOUND);
    }
}
