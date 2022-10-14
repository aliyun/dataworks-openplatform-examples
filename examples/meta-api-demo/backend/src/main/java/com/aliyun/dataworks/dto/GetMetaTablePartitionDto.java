package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 详细字段说明参考文档 https://help.aliyun.com/document_detail/173923.html
 */
@Data
public class GetMetaTablePartitionDto {

    private String dataSourceType;
    private String clusterId;
    private Integer pageNumber;
    private String tableGuid;
    private String databaseName;
    private Integer pageSize;
    private String tableName;

}
