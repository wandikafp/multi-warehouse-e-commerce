package com.anw.warehouse.service.messaging.publisher.kafka;

import com.anw.kafka.order.avro.model.JournalCreatedRequestAvroModel;
import com.anw.kafka.order.avro.model.TestRequestAvroModel;
import com.anw.kafka.producer.service.KafkaProducer;
import com.anw.warehouse.service.domain.config.WarehouseServiceConfig;
import com.anw.warehouse.service.domain.event.StockJournalCreatedEvent;
import com.anw.warehouse.service.domain.event.WarehouseCreatedEvent;
import com.anw.warehouse.service.domain.ports.output.message.publisher.StockJournalCreatedMessagePublisher;
import com.anw.warehouse.service.domain.ports.output.message.publisher.WarehouseCreatedTestRequestMessagePublisher;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Slf4j
@Component
public class StockJournalCreatedKafkaMessagePublisher implements StockJournalCreatedMessagePublisher {
    private final KafkaProducer<String, JournalCreatedRequestAvroModel> kafkaProducer;
    private final WarehouseServiceConfig warehouseServiceConfig;
    public StockJournalCreatedKafkaMessagePublisher(KafkaProducer<String, JournalCreatedRequestAvroModel> kafkaProducer,
                                                    WarehouseServiceConfig warehouseServiceConfig) {
       this.kafkaProducer = kafkaProducer;
       this.warehouseServiceConfig = warehouseServiceConfig;
    }

    @Override
    public void publish(StockJournalCreatedEvent domainEvent) {
        String stockId = domainEvent.getStockJournal().getId().toString();

        JournalCreatedRequestAvroModel requestAvroModel = JournalCreatedRequestAvroModel.newBuilder()
                .setProductId(domainEvent.getStockJournal().getProductId().getValue())
                .setQuantityChange(domainEvent.getStockJournal().getQuantityChange())
                .build();

        try {
            kafkaProducer.send(warehouseServiceConfig.getStockJournalCreatedRequestTopicName(),
                    stockId,
                    requestAvroModel);
        } catch (Exception e) {
            log.error("error while sending kafka message with stockId {}, error: {}", stockId, e.getMessage());
        }
    }
}
