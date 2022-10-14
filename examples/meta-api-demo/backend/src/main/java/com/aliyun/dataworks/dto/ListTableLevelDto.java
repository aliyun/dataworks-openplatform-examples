package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 详细字段说明参考文档 https://help.aliyun.com/document_detail/185673.html
 */
@Data
public class ListTableLevelDto {
    private Integer levelType;
    private Integer pageSize;
    private Integer pageNum;
    private Long projectId;
}
