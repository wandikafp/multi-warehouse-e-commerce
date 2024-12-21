package com.anw.product.service.domain.exception;

import com.anw.domain.exception.DomainException;

public class ProductDomainException extends DomainException {
    public ProductDomainException(String message) {
        super(message);
    }
    public ProductDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
