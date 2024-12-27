package com.anw.order.service.domain.exception;

import com.anw.domain.exception.DomainException;

public class OrderDomainException extends DomainException {
    public OrderDomainException(String message) {
        super(message);
    }
    public OrderDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
