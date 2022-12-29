package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 */
@Data
public class UpdateFolderDTO {

    /**
     * 文件夹ID
     */
    private String folderId;

    /**
     * 文件夹名称
     */
    private String folderName;

    /**
     * DataWorks工作空间的ID
     */
    private Long projectId;

}
