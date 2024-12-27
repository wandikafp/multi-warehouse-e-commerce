package com.anw.product.service.domain.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "product-service")
public class ProductServiceConfig {
    private String testRequestTopicName;
    private List<String> allowedOrigins;
}
