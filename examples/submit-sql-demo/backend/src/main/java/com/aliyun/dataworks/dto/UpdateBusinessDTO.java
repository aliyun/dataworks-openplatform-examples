package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * https://help.aliyun.com/document_detail/173941.html
 */
@Data
public class UpdateBusinessDTO {

    /**
     * 业务流程ID
     */
    private Long businessId;

    /**
     * dataworks 项目空间ID
     */
    private Long projectId;

    /**
     * 业务流程名称
     */
    private String businessName;

    /**
     * 业务流程描述
     */
    private String description;

    /**
     *
     */
    private String owner;
}
