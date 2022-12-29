package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 帮助文档：https://help.aliyun.com/document_detail/173955.html
 */
@Data
public class ListFoldersDTO {

    /**
     * 请求的数据页数，用于翻页
     */
    private Integer pageNumber;

    /**
     * 每页显示的数据条数，默认为10条，最大为100条。
     */
    private Integer pageSize;

    /**
     * 父文件夹的路径
     */
    private String parentFolderPath;

    /**
     * DataWorks工作空间的ID
     */
    private Long projectId;
}
