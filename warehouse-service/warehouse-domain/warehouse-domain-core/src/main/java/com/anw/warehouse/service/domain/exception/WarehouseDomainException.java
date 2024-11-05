package com.anw.warehouse.service.domain.exception;

import com.anw.domain.exception.DomainException;

public class WarehouseDomainException extends DomainException {
    public WarehouseDomainException(String message) {
        super(message);
    }
    public WarehouseDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
