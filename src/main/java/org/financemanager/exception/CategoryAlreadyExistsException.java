package org.financemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT, reason = "Category already exists")
public class CategoryAlreadyExistsException extends RuntimeException{
}
