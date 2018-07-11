package com.cn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "accesskey")
public class AccessKeyConfig {
    private String accessKeyId;
    private String accessKeySecret;
}
