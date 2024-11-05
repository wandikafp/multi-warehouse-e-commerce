package com.anw.warehouse.service.domain.exception;

import com.anw.domain.exception.DomainException;

public class StockDomainException extends DomainException {
    public StockDomainException(String message) {
        super(message);
    }
    public StockDomainException(String message, Throwable cause) {
        super(message, cause);
    }

}
