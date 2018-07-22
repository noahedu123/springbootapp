package com.cn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * 用户头像存放路径
 */
@Data
@Component
@ConfigurationProperties(prefix = "img")
public class ImageLocationConfig {
    private  String location;
}
