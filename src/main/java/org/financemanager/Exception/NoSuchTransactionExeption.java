package org.financemanager.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.NoSuchElementException;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "No such transaction")
public class NoSuchTransactionExeption extends NoSuchElementException {
}
