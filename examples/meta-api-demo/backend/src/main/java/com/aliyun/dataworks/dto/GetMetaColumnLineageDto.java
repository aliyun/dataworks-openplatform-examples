package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 详细字段说明参考文档 https://help.aliyun.com/document_detail/173928.html
 */
@Data
public class GetMetaColumnLineageDto {
    private String dataSourceType;
    private String clusterId;
    private Integer pageNum;
    private String columnName;
    private String columnGuid;
    private String databaseName;
    private Integer pageSize;
    private String tableName;
    private String direction;
}
