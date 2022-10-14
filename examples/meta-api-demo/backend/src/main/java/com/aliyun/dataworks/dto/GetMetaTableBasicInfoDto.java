package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 详细字段说明参考文档 https://help.aliyun.com/document_detail/173920.html
 */
@Data
public class GetMetaTableBasicInfoDto {

    private String dataSourceType;
    private Boolean extension;
    private String tableGuid;
    private String databaseName;
    private String clusterId;
    private String tableName;

}
