package com.aliyun.dataworks.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dataworks demo
 */
@Data
@Component
@ConfigurationProperties("api")
public class ApiParamProperties {

    private String regionId;
    private String endpoint;
    private String accessKeyId;
    private String accessKeySecret;
    private String product;
    private boolean vpcEnv = false;
}
