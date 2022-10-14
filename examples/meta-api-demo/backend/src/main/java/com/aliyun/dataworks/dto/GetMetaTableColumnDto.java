package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 详细字段说明参考文档 https://help.aliyun.com/document_detail/173921.html
 */
@Data
public class GetMetaTableColumnDto {

    private String dataSourceType;
    private String clusterId;
    private Integer pageNum;
    private String tableGuid;
    private String databaseName;
    private Integer pageSize;
    private String tableName;

}
