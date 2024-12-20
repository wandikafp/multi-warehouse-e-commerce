package com.anw.user.service.domain;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.user.service.domain.entity.User;
import com.anw.user.service.domain.event.UserRegisteredEvent;

public interface UserDomainService {
    UserRegisteredEvent registerUser(User user, DomainEventPublisher<UserRegisteredEvent>
                                                    orderCreatedEventDomainEventPublisher);
}
