package com.aliyun.dataworks.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dataworks demo
 */
@Data
@Component
@ConfigurationProperties("extensions")
public class ExtensionParamProperties {

    private String extensionCode;

    private String holidayList;
}
