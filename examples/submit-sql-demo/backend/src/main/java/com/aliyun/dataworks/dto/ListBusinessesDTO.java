package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 参考文档https://help.aliyun.com/document_detail/173945.html
 */
@Data
public class ListBusinessesDTO {

    /**
     * 请求的数据页数
     */
    private Integer pageNumber;

    /**
     * 每页显示的数据条数
     */
    private Integer pageSize;

    /**
     * DataWorks工作空间的ID
     */
    private Long projectId;

    /**
     * 关键字，用于模糊匹配业务流程的名称。
     */
    private String keyword;
}
