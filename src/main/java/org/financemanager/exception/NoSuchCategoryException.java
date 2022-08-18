package org.financemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such category")
public class NoSuchCategoryException extends NoSuchElementException {
    public NoSuchCategoryException(String description){
        super(description);
    }
}
