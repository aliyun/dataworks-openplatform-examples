package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 详细字段说明参考文档 https://help.aliyun.com/document_detail/185669.html
 */
@Data
public class CreateTableLevelDto {
    private Integer levelType;
    private String name;
    private String description;
    private Long projectId;
}
