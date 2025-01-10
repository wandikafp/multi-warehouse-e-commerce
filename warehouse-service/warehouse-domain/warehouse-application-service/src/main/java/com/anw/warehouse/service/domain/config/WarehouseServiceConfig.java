package com.anw.warehouse.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "warehouse-service")
public class WarehouseServiceConfig {
    private String testRequestTopicName;
    private String stockJournalCreatedRequestTopicName;
    private List<String> allowedOrigins;
}
