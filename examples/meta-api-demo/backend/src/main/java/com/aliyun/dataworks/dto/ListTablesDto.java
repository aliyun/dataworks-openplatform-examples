package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 详细字段说明参考文档 https://help.aliyun.com/document_detail/173916.html
 */
@Data
public class ListTablesDto {

    /**
     * 数据类型，目前仅支持odps和emr。
     */
    private String dataSourceType;

    /**
     * 数据库的名称。
     */
    private String databaseName;

    /**
     * 每页显示的条数，默认为10条，最大为100条。
     */
    private Integer pageSize = 10;

    /**
     * EMR集群的ID，仅当数据类型为emr时，需要配置该参数。
     */
    private String clusterId;

    /**
     * 项目的唯一标识，格式为odps.{projectName}。仅当数据类型为odps时，需要配置该参数。
     */
    private String appGuid;

    /**
     * 请求的数据页数，用于翻页，默认为1。
     */
    private Integer pageNumber = 1;

}
