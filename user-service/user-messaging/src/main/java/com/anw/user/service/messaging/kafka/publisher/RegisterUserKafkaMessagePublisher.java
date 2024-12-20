package com.anw.user.service.messaging.kafka.publisher;

import com.anw.domain.valueobject.BaseId;
import com.anw.kafka.order.avro.model.RegisterUserRequestAvroModel;
import com.anw.kafka.producer.service.KafkaProducer;
import com.anw.user.service.domain.config.UserServiceConfig;
import com.anw.user.service.domain.event.UserRegisteredEvent;
import com.anw.user.service.domain.ports.output.message.UserRegisteredMessagePublisher;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterUserKafkaMessagePublisher implements UserRegisteredMessagePublisher {
    private final KafkaProducer<String, RegisterUserRequestAvroModel> kafkaProducer;
    private final UserServiceConfig userServiceConfig;
    @Override
    public void publish(UserRegisteredEvent domainEvent) {
        String userId = domainEvent.getUser().getId().getValue().toString();
        log.info("User: {}", domainEvent.getUser());

        RegisterUserRequestAvroModel registerUserRequest = RegisterUserRequestAvroModel.newBuilder()
                .setId(domainEvent.getUser().getId().getValue())
                .setEmail(domainEvent.getUser().getEmail())
                .setFullName(domainEvent.getUser().getFullName())
                .setRole(domainEvent.getUser().getRole().toString())
                .setWarehouseId(Optional.ofNullable(domainEvent.getUser().getWarehouseId())
                        .map(BaseId::getValue)
                        .orElse(null))
                .build();

        try {
            kafkaProducer.send(userServiceConfig.getRegisterUserTopicName(),
                    userId,
                    registerUserRequest);
        } catch (Exception e) {
            log.error("error while sending kafka message with userId {}, error: {}", userId, e.getMessage());
        }
    }
}
