package com.anw.warehouse.service.messaging.publisher.kafka;

import com.anw.kafka.order.avro.model.TestRequestAvroModel;
import com.anw.kafka.producer.KafkaMessageHelper;
import com.anw.kafka.producer.service.KafkaProducer;
import com.anw.warehouse.service.domain.config.WarehouseServiceConfig;
import com.anw.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.anw.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedTestRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class TestKafkaMessagePublisher implements WarehouseCreatedTestRequestMessagePublisher {
    private final KafkaProducer<String, TestRequestAvroModel> kafkaProducer;
    private final WarehouseServiceConfig warehouseServiceConfig;
    public TestKafkaMessagePublisher(KafkaProducer<String, TestRequestAvroModel> kafkaProducer,
                                     WarehouseServiceConfig warehouseServiceConfig) {
       this.kafkaProducer = kafkaProducer;
       this.warehouseServiceConfig = warehouseServiceConfig;
    }
    @Override
    public void publish(WarehouseCreatedEvent domainEvent) {
        String warehouseId = domainEvent.getWarehouse().getId().getValue().toString();

        TestRequestAvroModel test = TestRequestAvroModel.newBuilder()
                .setId(UUID.randomUUID())
                .setWarehouseId(warehouseId)
                .setMessage("test publish message")
                .build();

        try {
            kafkaProducer.send(warehouseServiceConfig.getTestRequestTopicName(),
                    warehouseId,
                    test);
        } catch (Exception e) {
            log.error("error while sending kafka message with warehouseId {}, error: {}", warehouseId, e.getMessage());
        }
    }
}
