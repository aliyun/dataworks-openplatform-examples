package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * https://help.aliyun.com/document_detail/173954.html
 */
@Data
public class GetFileDTO {

    /**
     * DataWorks工作空间的ID
     */
    private Long projectId;

    /**
     * 文件的ID
     */
    private Long fileId;

    /**
     * 调度节点的ID
     */
    private Long nodeId;
}
