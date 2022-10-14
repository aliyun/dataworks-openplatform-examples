package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 详细字段说明参考文档 https://help.aliyun.com/document_detail/185664.html
 */
@Data
public class CreateTableThemeDto {
    private Integer level;
    private String name;
    private Long projectId;
    private Long parentId;
}
