package com.anw.warehouse.service.messaging.listener.kafka;

import com.anw.kafka.consumer.KafkaConsumer;
import com.anw.kafka.order.avro.model.RegisterUserRequestAvroModel;
import com.anw.warehouse.service.domain.ports.input.message.listener.user.UserMessageListener;
import com.anw.warehouse.service.messaging.mapper.WarehouseMessagingDataMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
@RequiredArgsConstructor
public class RegisterUserKafkaMessageListener implements KafkaConsumer<RegisterUserRequestAvroModel> {
    private final UserMessageListener userMessageListener;
    private final WarehouseMessagingDataMapper warehouseMessagingDataMapper;
    @Override
    @KafkaListener(id = "${kafka-consumer-config.warehouse-group-id}", topics = "${warehouse-service.register-user-topic-name}" )
    public void receive(@Payload List<RegisterUserRequestAvroModel> messages,
                        @Header(KafkaHeaders.RECEIVED_KEY) List<String> keys,
                        @Header(KafkaHeaders.RECEIVED_PARTITION) List<Integer> partitions,
                        @Header(KafkaHeaders.OFFSET) List<Long> offsets) {
        log.info("{} number of registered users received with keys: {}, partitions: {}, and offsets: {}",
                messages.size(),
                keys.toString(),
                partitions.toString(),
                offsets.toString());

        messages.forEach(registerUserRequestAvroModel -> {
            log.info("handling message with userId {} and payload {}",
                    registerUserRequestAvroModel.getId().toString(),
                    registerUserRequestAvroModel.toString());
            userMessageListener.userRegistered(
                    warehouseMessagingDataMapper.registerUserRequestAvroModelToUserResponse(registerUserRequestAvroModel));
        });

    }
}
