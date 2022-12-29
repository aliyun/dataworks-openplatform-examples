package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 */
@Data
public class DeleteFolderDTO {

    /**
     * 文件夹的ID
     */
    private String folderId;

    /**
     * DataWorks工作空间的ID
     */
    private Long projectId;
}
