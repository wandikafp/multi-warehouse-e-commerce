package com.anw.user.service.domain.ports.output.message;

import com.anw.domain.event.publisher.DomainEventPublisher;
import com.anw.user.service.domain.event.UserRegisteredEvent;

public interface UserRegisteredMessagePublisher  extends DomainEventPublisher<UserRegisteredEvent> {
}
