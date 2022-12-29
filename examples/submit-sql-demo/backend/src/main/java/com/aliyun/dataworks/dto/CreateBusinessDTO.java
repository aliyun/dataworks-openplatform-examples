package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 */
@Data
public class CreateBusinessDTO {

    /**
     * 业务流程名称
     */
    private String businessName;

    /**
     * DataWorks工作空间的ID
     */
    private Long projectId;

    /**
     * 业务流程的描述信息
     */
    private String description;

    /**
     * 业务流程对应责任人的阿里云账号ID
     */
    private String owner;

    /**
     * 业务流程所属的功能模块。取值如下：
     * <p>
     * NORMAL（数据开发）
     * MANUAL_BIZ（手动业务流程）
     */
    private String useType;
}
