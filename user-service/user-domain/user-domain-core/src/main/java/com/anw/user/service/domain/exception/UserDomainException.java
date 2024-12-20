package com.anw.user.service.domain.exception;

import com.anw.domain.exception.DomainException;
import lombok.Builder;

import java.util.Map;

public class UserDomainException extends DomainException {
    public UserDomainException(String message) {
        super(message);
    }
    public UserDomainException(String message, Throwable cause) {
        super(message, cause);
    }
}
