package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 */
@Data
public class CreateFolderDTO {

    /**
     * 文件夹的路径
     */
    private String folderPath;

    /**
     * DataWorks工作空间的ID
     */
    private Long projectId;
}
