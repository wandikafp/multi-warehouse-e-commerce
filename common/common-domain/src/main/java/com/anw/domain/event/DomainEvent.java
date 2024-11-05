package com.anw.domain.event;

public interface DomainEvent<T> {
    void fire();
}
