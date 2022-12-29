package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 参考文档：https://help.aliyun.com/document_detail/173949.html
 */
@Data
public class DeleteFileDTO {

    /**
     * DataWorks工作空间的ID
     */
    private Long projectId;

    /**
     * 文件的ID
     */
    private Long fileId;
}
