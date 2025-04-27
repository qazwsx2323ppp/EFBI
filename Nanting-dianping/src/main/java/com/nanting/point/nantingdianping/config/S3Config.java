package com.nanting.point.nantingdianping.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "aws.s3")
@Data
public class S3Config {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
