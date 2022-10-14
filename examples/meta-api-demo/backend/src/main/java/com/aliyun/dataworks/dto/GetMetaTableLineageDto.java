package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 详细字段说明参考文档 https://help.aliyun.com/document_detail/173927.html
 */
@Data
public class GetMetaTableLineageDto {
    private String dataSourceType;
    private String clusterId;
    private String tableGuid;
    private String nextPrimaryKey;
    private String databaseName;
    private Integer pageSize;
    private String tableName;
    private String direction;
}
