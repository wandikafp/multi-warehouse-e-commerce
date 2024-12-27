package com.anw.dataaccess.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties(prefix = "gcp-config")
public class GCPConfig {
    private String configFile;
    private String projectId;
    private String bucketId;
    private String directoryName;
}
