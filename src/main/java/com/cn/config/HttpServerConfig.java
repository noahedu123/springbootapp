package com.cn.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *  httpServer参数
 */
@Data
@Component
@ConfigurationProperties(prefix = "httpserver")
public class HttpServerConfig
{
    //用户名
    private String username;
    //密码
    private String psw;
    //IP
    private String ip;
    //端口 <=0 为默认端口
    private int port;
    //上传的文件服务器根路径
    private String filehome;
    //http服务用户访问路径
    private String accesspath;
    //http端口
    private int httpport;
}
