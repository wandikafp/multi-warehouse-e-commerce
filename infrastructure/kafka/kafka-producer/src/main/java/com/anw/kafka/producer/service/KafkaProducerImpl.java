package com.anw.kafka.producer.service;


import com.anw.kafka.producer.exception.KafkaProducerException;
import jakarta.annotation.PreDestroy;
import lombok.extern.slf4j.Slf4j;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.io.JsonEncoder;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.KafkaException;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

@Slf4j
@Component
public class KafkaProducerImpl<K extends Serializable, V extends SpecificRecordBase> implements KafkaProducer<K, V> {

    private final KafkaTemplate<K, V> kafkaTemplate;

    public KafkaProducerImpl(KafkaTemplate<K, V> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(String topicName, K key, V message) {
        log.info("Sending message={} to topic={}", message, topicName);
        try {
            kafkaTemplate.send(topicName, key, message).whenComplete(
                    (kvSendResult, throwable) -> {
                        if (throwable != null) {
                            log.error("Error while sending message {} to topic {}: {}", message.toString(), topicName, throwable.getMessage());
                        } else {
                            RecordMetadata metadata = kvSendResult.getRecordMetadata();
                            log.info("Received successful response from Kafka for id: {}" +
                                            " Topic: {} Partition: {} Offset: {} Timestamp: {}",
                                    key,
                                    metadata.topic(),
                                    metadata.partition(),
                                    metadata.offset(),
                                    metadata.timestamp());
                        }
                    });
        } catch (KafkaException e) {
            log.error("Error on kafka producer with key: {}, message: {} and exception: {}", key, message,
                    e.getMessage());
            throw new KafkaProducerException("Error on kafka producer with key: " + key + " and message: " + message);
        }
    }

    @PreDestroy
    public void close() {
        if (kafkaTemplate != null) {
            log.info("Closing kafka producer!");
            kafkaTemplate.destroy();
        }
    }

    private String convertAvroToJsonString(GenericRecord avroRecord) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        JsonEncoder encoder = EncoderFactory.get().jsonEncoder(avroRecord.getSchema(), outputStream);
        GenericDatumWriter<GenericRecord> writer = new GenericDatumWriter<>(avroRecord.getSchema());
        writer.write(avroRecord, encoder);
        encoder.flush();
        outputStream.close();
        return outputStream.toString();
    }
}
