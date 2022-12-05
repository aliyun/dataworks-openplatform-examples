package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 */
@Data
public class DeleteBusinessDTO {

    /**
     * 业务流程的ID
     */
    private Long businessId;

    /**
     * DataWorks项目空间ID
     */
    private Long projectId;
}
