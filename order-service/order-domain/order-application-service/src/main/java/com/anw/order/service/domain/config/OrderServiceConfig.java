package com.anw.order.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "order-service")
public class OrderServiceConfig {
    private String testRequestTopicName;
    private List<String> allowedOrigins;
}
