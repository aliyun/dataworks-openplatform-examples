package com.aliyun.dataworks.services;

import com.aliyun.dataworks.dto.*;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dataworks_public.model.v20200518.*;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

/**
 * @author dataworks demo
 */
@Service
public class MetaServiceProxy {

  @Autowired
  private DataWorksOpenApiClient dataWorksOpenApiClient;

  /**
   * DataWorks OpenAPI : GetMetaTableBasicInfo
   * Link : https://help.aliyun.com/document_detail/173920.html
   *
   * @param getMetaTableBasicInfoDto
   */
  public GetMetaTableBasicInfoResponse.Data getMetaTableBasicInfo(GetMetaTableBasicInfoDto getMetaTableBasicInfoDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      GetMetaTableBasicInfoRequest getMetaTableBasicInfoRequest = new GetMetaTableBasicInfoRequest();
      // MaxCompute表的唯一标识。格式为odps.projectName.tableName。
      getMetaTableBasicInfoRequest.setTableGuid(getMetaTableBasicInfoDto.getTableGuid());
      // 数据库名称
      getMetaTableBasicInfoRequest.setDatabaseName(getMetaTableBasicInfoDto.getDatabaseName());
      // EMR的表名称
      getMetaTableBasicInfoRequest.setTableName(getMetaTableBasicInfoDto.getTableName());
      // 数据类型，包括odps和emr。
      getMetaTableBasicInfoRequest.setDataSourceType("odps");
      // 是否包含扩展字段。扩展字段包含读取次数、收藏次数、浏览次数等。仅当数据类型为ODPS时，该参数生效。
      getMetaTableBasicInfoRequest.setExtension(true);
      GetMetaTableBasicInfoResponse acsResponse = client.getAcsResponse(getMetaTableBasicInfoRequest);
      // 表的名称。
      System.out.println(acsResponse.getData().getTableName());
      // 表的收藏次数。仅当Extension参数取值为true时才会返回该参数，并且该参数仅对odps数据类型生效。
      System.out.println(acsResponse.getData().getFavoriteCount());
      // 表的描述。
      System.out.println(acsResponse.getData().getComment());
      // 字段的个数。
      System.out.println(acsResponse.getData().getColumnCount());
      // 创建表的时间。
      System.out.println(acsResponse.getData().getCreateTime());
      // 工作空间的ID。
      System.out.println(acsResponse.getData().getProjectId());
      // 表所有者的ID。
      System.out.println(acsResponse.getData().getOwnerId());
      // 环境类型，取值如下：
      // 0表示开发表。
      // 1表示生产表。
      System.out.println(acsResponse.getData().getEnvType());
      // 数据库的名称。
      System.out.println(acsResponse.getData().getDatabaseName());
      // 表的可见性：0表示目标表对工作空间成员可见。
      // 1表示目标表对租户内成员可见。
      // 2表示目标表对租户间成员均可见。
      // 3表示目标表仅对责任人可见。
      System.out.println(acsResponse.getData().getIsVisible());
      // 表的唯一标识。
      System.out.println(acsResponse.getData().getTableGuid());
      // 表的读取次数。仅当Extension参数取值为true时才会返回该参数，并且该参数仅对odps数据类型生效。
      System.out.println(acsResponse.getData().getReadCount());
      // EMR集群的ID。
      System.out.println(acsResponse.getData().getClusterId());
      // 是否为分区表，取值如下：
      // true：是分区表。
      // false：不是分区表。
      System.out.println(acsResponse.getData().getIsPartitionTable());
      // 是否为分区表，取值如下：
      // true：是分区表。
      // false：不是分区表。
      System.out.println(acsResponse.getData().getIsView());
      // 表的生命周期。单位为天。
      System.out.println(acsResponse.getData().getLifeCycle());
      // 工作空间的名称。
      System.out.println(acsResponse.getData().getProjectName());
      // 表的浏览次数。仅当Extension参数取值为true时才会返回该参数，并且该参数仅对odps数据类型生效。
      System.out.println(acsResponse.getData().getViewCount());
      // 最近一次访问表的时间。
      System.out.println(acsResponse.getData().getLastAccessTime());
      // 表占用的存储空间。单位为Byte。
      System.out.println(acsResponse.getData().getDataSize());
      // 最近一次更新表的时间。
      System.out.println(acsResponse.getData().getLastModifyTime());
      // 最近一次变更表结构的时间。
      System.out.println(acsResponse.getData().getLastDdlTime());
      // Hive分区。
      System.out.println(acsResponse.getData().getPartitionKeys());
      // Hive数据库的存储地址。
      System.out.println(acsResponse.getData().getLocation());
      // 表的中文名称。
      System.out.println(acsResponse.getData().getCaption());
      // 租户ID。
      System.out.println(acsResponse.getData().getTenantId());
      return acsResponse.getData();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

  /**
   * DataWorks OpenAPI : GetMetaTableColumn
   * Link : https://help.aliyun.com/document_detail/173921.html
   *
   * @param getMetaTableColumnDto
   */
  public GetMetaTableColumnResponse.Data getMetaTableColumn(GetMetaTableColumnDto getMetaTableColumnDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      GetMetaTableColumnRequest getMetaTableColumnRequest = new GetMetaTableColumnRequest();
      // 表的唯一标识。
      getMetaTableColumnRequest.setTableGuid(getMetaTableColumnDto.getTableGuid());
      // 请求获取的数据页码数，用于翻页。
      getMetaTableColumnRequest.setPageNum(getMetaTableColumnDto.getPageNum());
      // 每页显示的条数，默认为10条，最大100条。
      getMetaTableColumnRequest.setPageSize(getMetaTableColumnDto.getPageSize());
      // EMR集群的ID，您可以登录EMR管理控制台，获取集群ID。
      getMetaTableColumnRequest.setClusterId(getMetaTableColumnDto.getClusterId());
      // EMR的数据库名称
      getMetaTableColumnRequest.setDatabaseName(getMetaTableColumnDto.getDatabaseName());
      // EMR的数据库名称
      getMetaTableColumnRequest.setTableName(getMetaTableColumnDto.getTableName());
      // 数据类型，当前仅支持取值为emr。
      getMetaTableColumnRequest.setDataSourceType(getMetaTableColumnDto.getDataSourceType());
      GetMetaTableColumnResponse acsResponse = client.getAcsResponse(getMetaTableColumnRequest);
      // 字段的总数。
      System.out.println(acsResponse.getData().getTotalCount());
      for (GetMetaTableColumnResponse.Data.ColumnListItem columnListItem : acsResponse.getData()
          .getColumnList()) {
        // 字段的唯一标识。
        System.out.println(columnListItem.getColumnGuid());
        // 字段的名称。
        System.out.println(columnListItem.getColumnName());
        // 字段是否为分区字段，取值如下：
        // true，是分区字段。
        // false，不是分区字段。
        System.out.println(columnListItem.getIsPartitionColumn());
        // 字段的备注。
        System.out.println(columnListItem.getComment());
        // 字段的类型。
        System.out.println(columnListItem.getColumnType());
        // 字段是否为主键，取值如下：
        // true，是主键。
        // false，不是主键。
        System.out.println(columnListItem.getIsPrimaryKey());
        // 字段的排序。
        System.out.println(columnListItem.getPosition());
        // 字段的描述。
        System.out.println(columnListItem.getCaption());
        // 字段是否为外键，取值如下：
        // true，是外键。
        // false，不是外键。
        System.out.println(columnListItem.getIsForeignKey());
        // 字段热度。
        System.out.println(columnListItem.getRelationCount());
      }
      return acsResponse.getData();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

  /**
   * DataWorks OpenAPI : GetMetaTablePartition
   * Link : https://help.aliyun.com/document_detail/173923.html
   *
   * @param getMetaTablePartitionDto
   */
  public GetMetaTablePartitionResponse.Data getMetaTablePartition(GetMetaTablePartitionDto getMetaTablePartitionDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      GetMetaTablePartitionRequest getMetaTablePartitionRequest = new GetMetaTablePartitionRequest();
      // 请求的数据页数，用于翻页。
      getMetaTablePartitionRequest.setPageNumber(getMetaTablePartitionDto.getPageNumber());
      // 每页显示的条数，默认为10条，最大100条。
      getMetaTablePartitionRequest.setPageSize(getMetaTablePartitionDto.getPageSize());
      // 表的唯一标识。
      getMetaTablePartitionRequest.setTableGuid(getMetaTablePartitionDto.getTableGuid());
      // EMR集群的ID，仅当数据类型为EMR时，需要配置该参数。
      getMetaTablePartitionRequest.setClusterId(getMetaTablePartitionDto.getClusterId());
      // 数据库的名称。仅当数据类型为EMR时，需要配置该参数。
      getMetaTablePartitionRequest.setDatabaseName(getMetaTablePartitionDto.getDatabaseName());
      // 数据库的名称。仅当数据类型为EMR时，需要配置该参数。
      getMetaTablePartitionRequest.setTableName(getMetaTablePartitionDto.getTableName());
      // 数据类型，支持ODPS或者EMR。
      getMetaTablePartitionRequest.setDataSourceType(getMetaTablePartitionDto.getDataSourceType());
      GetMetaTablePartitionResponse acsResponse = client.getAcsResponse(getMetaTablePartitionRequest);
      for (GetMetaTablePartitionResponse.Data.DataEntityListItem dataEntityListItem : acsResponse.getData()
          .getDataEntityList()) {
        // 分区的目录。
        System.out.println(dataEntityListItem.getPartitionPath());
        // 分区的大小，单位为Byte。
        System.out.println(dataEntityListItem.getDataSize());
        // 分区的名称。
        System.out.println(dataEntityListItem.getPartitionName());
        // 备注信息。
        System.out.println(dataEntityListItem.getComment());
        // 修改分区的时间。
        System.out.println(dataEntityListItem.getModifiedTime());
        // 创建分区的时间。
        System.out.println(dataEntityListItem.getCreateTime());
        // 分区的数据量。
        System.out.println(dataEntityListItem.getRecordCount());
        // 分区的类型。
        System.out.println(dataEntityListItem.getPartitionType());
        // 分区的唯一标识。
        System.out.println(dataEntityListItem.getPartitionGuid());
        // Hive分区的地址。
        System.out.println(dataEntityListItem.getPartitionLocation());
        // 表的唯一标识。
        System.out.println(dataEntityListItem.getTableGuid());
      }
      return acsResponse.getData();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

  /**
   * DataWorks OpenAPI : GetMetaTableLineage
   * Link : https://help.aliyun.com/document_detail/173927.html
   *
   * @param getMetaTableLineageDto
   */
  public GetMetaTableLineageResponse.Data getMetaTableLineage(GetMetaTableLineageDto getMetaTableLineageDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      GetMetaTableLineageRequest getMetaTableLineageRequest = new GetMetaTableLineageRequest();
      // 表的唯一标识。
      getMetaTableLineageRequest.setTableGuid(getMetaTableLineageDto.getTableGuid());
      // 字段的上下游方向：up表示上游，down表示下游。
      getMetaTableLineageRequest.setDirection(getMetaTableLineageDto.getDirection());
      // 分页数据。
      getMetaTableLineageRequest.setNextPrimaryKey(getMetaTableLineageDto.getTableGuid());
      // 每页显示的条数，默认为10条，最大100条。
      getMetaTableLineageRequest.setPageSize(getMetaTableLineageDto.getPageSize());
      // EMR集群的ID，针对EMR情况。
      getMetaTableLineageRequest.setClusterId(getMetaTableLineageDto.getClusterId());
      // 数据库的名称。
      getMetaTableLineageRequest.setDatabaseName(getMetaTableLineageDto.getDatabaseName());
      // 表名
      getMetaTableLineageRequest.setTableName(getMetaTableLineageDto.getTableName());
      // 数据类型，包括odps或emr。
      getMetaTableLineageRequest.setDataSourceType(getMetaTableLineageDto.getDataSourceType());
      GetMetaTableLineageResponse acsResponse = client.getAcsResponse(getMetaTableLineageRequest);
      for (GetMetaTableLineageResponse.Data.DataEntityListItem dataEntityListItem : acsResponse.getData()
          .getDataEntityList()) {
        // 表的名称。
        System.out.println(dataEntityListItem.getTableName());
        // 表的唯一标识。
        System.out.println(dataEntityListItem.getTableGuid());
        // 创建时间。
        System.out.println(dataEntityListItem.getCreateTimestamp());
      }
      return acsResponse.getData();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

  /**
   * DataWorks OpenAPI : GetMetaColumnLineage
   * Link : https://help.aliyun.com/document_detail/173928.html
   *
   * @param getMetaColumnLineageDto
   */
  public GetMetaColumnLineageResponse.Data getMetaColumnLineage(GetMetaColumnLineageDto getMetaColumnLineageDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      GetMetaColumnLineageRequest getMetaColumnLineageRequest = new GetMetaColumnLineageRequest();
      // 字段的唯一标识。
      getMetaColumnLineageRequest.setColumnGuid(getMetaColumnLineageDto.getColumnGuid());
      // 字段的上下游方向：up表示上游，down表示下游。
      getMetaColumnLineageRequest.setDirection(getMetaColumnLineageDto.getDirection());
      // 请求的数据页数，用于翻页。
      getMetaColumnLineageRequest.setPageNum(getMetaColumnLineageDto.getPageNum());
      // 每页显示的条数，默认为10条，最大100条。
      getMetaColumnLineageRequest.setPageSize(getMetaColumnLineageDto.getPageSize());
      // EMR集群的ID，针对EMR情况。
      getMetaColumnLineageRequest.setClusterId(getMetaColumnLineageDto.getClusterId());
      // 数据库的名称。
      getMetaColumnLineageRequest.setDatabaseName(getMetaColumnLineageDto.getDatabaseName());
      // 表名称。
      getMetaColumnLineageRequest.setTableName(getMetaColumnLineageDto.getTableName());
      // 字段名称。
      getMetaColumnLineageRequest.setColumnName(getMetaColumnLineageDto.getColumnName());
      // 数据类型，包括odps或emr。
      getMetaColumnLineageRequest.setDataSourceType(getMetaColumnLineageDto.getDataSourceType());
      GetMetaColumnLineageResponse acsResponse = client.getAcsResponse(getMetaColumnLineageRequest);
      // 字段的总数。
      System.out.println(acsResponse.getData().getTotalCount());
      for (GetMetaColumnLineageResponse.Data.DataEntityListItem dataEntityListItem : acsResponse.getData()
          .getDataEntityList()) {
        // emr集群ID
        System.out.println(dataEntityListItem.getClusterId());
        // 字段的唯一标识。
        System.out.println(dataEntityListItem.getColumnGuid());
        // 字段的名称。
        System.out.println(dataEntityListItem.getColumnName());
        // 数据库名称
        System.out.println(dataEntityListItem.getDatabaseName());
        // 表名
        System.out.println(dataEntityListItem.getTableName());
      }
      return acsResponse.getData();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

  /**
   * DataWorks OpenAPI : GetMetaDBTableList
   * Link : https://help.aliyun.com/document_detail/173916.html
   *
   * @param listTablesDto
   */
  public GetMetaDBTableListResponse.Data getMetaDBTableList(ListTablesDto listTablesDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      GetMetaDBTableListRequest getMetaDBTableListRequest = new GetMetaDBTableListRequest();
      if (StringUtils.isEmpty(listTablesDto.getDataSourceType())) {
        // 数据类型，目前仅支持odps和emr。
        getMetaDBTableListRequest.setDataSourceType("odps");
      }
      // 项目的唯一标识，格式为odps.{projectName}。仅当数据类型为odps时，需要配置该参数。
      getMetaDBTableListRequest.setAppGuid(listTablesDto.getAppGuid());
      // 请求的数据页数，用于翻页。
      getMetaDBTableListRequest.setPageNumber(listTablesDto.getPageNumber());
      // 每页显示的条数，默认为10条，最大为100条。
      getMetaDBTableListRequest.setPageSize(listTablesDto.getPageSize());
      // 数据库的名称。
      getMetaDBTableListRequest.setDatabaseName(listTablesDto.getDatabaseName());
      GetMetaDBTableListResponse acsResponse = client.getAcsResponse(getMetaDBTableListRequest);
      // 计算引擎的总数
      System.out.println(acsResponse.getData().getTotalCount());
      for (GetMetaDBTableListResponse.Data.TableEntityListItem tableEntityListItem : acsResponse.getData()
          .getTableEntityList()) {
        // 表的唯一标识
        System.out.println(tableEntityListItem.getTableGuid());
        // 表的名称
        System.out.println(tableEntityListItem.getTableName());
        // 数据库的名称
        System.out.println(tableEntityListItem.getDatabaseName());
      }
      return acsResponse.getData();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

  /**
   * DataWorks OpenAPI : createTableTheme
   * Link : https://help.aliyun.com/document_detail/185664.html
   *
   * @param createTableThemeDto
   */
  public Long createTableTheme(CreateTableThemeDto createTableThemeDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      CreateTableThemeRequest createTableThemeRequest = new CreateTableThemeRequest();
      // 主题的层级，包括1（一级主题）和2（二级主题）。
      createTableThemeRequest.setLevel(createTableThemeDto.getLevel());
      // 主题的名称。
      createTableThemeRequest.setName(createTableThemeDto.getName());
      // DataWorks工作空间的ID。
      createTableThemeRequest.setProjectId(createTableThemeDto.getProjectId());
      // 父类层级ID。
      createTableThemeRequest.setParentId(createTableThemeDto.getParentId());
      CreateTableThemeResponse acsResponse = client.getAcsResponse(createTableThemeRequest);
      // 生成的主题ID。
      System.out.println(acsResponse.getThemeId());
      return acsResponse.getThemeId();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

  /**
   * DataWorks OpenAPI : CreateTable
   * Link : https://help.aliyun.com/document_detail/185656.html
   *
   * @param createTableDto
   */
  public boolean createTable(CreateTableDto createTableDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      CreateTableRequest createTableRequest = new CreateTableRequest();
      // 指定创建视图或创建表：
      // 0为创建表。
      // 1为创建视图。
      createTableRequest.setIsView(createTableDto.getIsView());
      // 表或工作空间是否可见：
      // 0为表和工作空间均不可见。
      // 1为表和工作空间均可见。
      // 2为仅工作空间可见。
      createTableRequest.setVisibility(createTableDto.getVisibility());
      // 表的生命周期。默认取值为空，表示永久存储。
      createTableRequest.setLifeCycle(createTableDto.getLifeCycle());
      // 关联类目的ID。
      createTableRequest.setCategoryId(createTableDto.getCategoryId());
      // 逻辑层级ID。
      createTableRequest.setLogicalLevelId(createTableDto.getLogicalLevelId());
      // 物理层级ID。
      createTableRequest.setPhysicsLevelId(createTableDto.getPhysicsLevelId());
      // 外部表的存储类型。取值如下：
      // 0表示OSS。
      // 1表示TableStore。
      // 2表示Volume。
      // 3表示MySQL。
      createTableRequest.setExternalTableType(createTableDto.getExternalTableType());
      // 外部表的存储地址。
      createTableRequest.setLocation(createTableDto.getLocation());
      // DataWorks工作空间的ID。
      createTableRequest.setProjectId(createTableDto.getProjectId());
      // 表的名称。
      createTableRequest.setTableName(createTableDto.getTableName());
      // MaxCompute的Endpoint。
      createTableRequest.setEndpoint(createTableDto.getEndpoint());
      // DataWorks工作空间的环境。取值如下：
      // 0表示开发环境。
      // 1表示生产环境。
      createTableRequest.setEnvType(createTableDto.getEnvType());
      // MaxCompute项目的ID，格式为odps.{projectName}。
      createTableRequest.setAppGuid(createTableDto.getAppGuid());
      // 备注信息。
      createTableRequest.setComment(createTableDto.getComment());
      // 创建的MaxCompute表是否为分区表，包括1（是）和0（否）。该字段已废弃，请勿使用。
      createTableRequest.setHasPart(createTableDto.getHasPart());
      // 保留字段。
      createTableRequest.setClientToken(createTableDto.getClientToken());
      if (!CollectionUtils.isEmpty(createTableDto.getColumns())) {
        int i = 0;
        for (CreateTableRequest.Columns columns : createTableDto.getColumns()) {
          columns.setSeqNumber(++i);
        }
        // 字段属性
        createTableRequest.setColumnss(createTableDto.getColumns());
      }

      // 主题属性
      createTableRequest.setThemess(createTableDto.getThemes());
      CreateTableResponse acsResponse = client.getAcsResponse(createTableRequest);
      // 当前执行的子任务的状态信息。取值如下：
      // operating表示子任务正在执行中。
      // success表示子任务执行成功。
      // failure表示子任务执行失败。详细的报错信息请参见Content参数。
      System.out.println(acsResponse.getTaskInfo().getStatus());
      // 即将执行的子任务ID。如果该字段为空，则表示所有子任务均已结束。
      System.out.println(acsResponse.getTaskInfo().getNextTaskId());
      // 当前执行的子任务ID。
      System.out.println(acsResponse.getTaskInfo().getTaskId());
      // 当前子任务的执行状态详细信息。具体如下：
      // 执行成功，则显示success。
      // 执行失败则显示对应的报错详情。
      System.out.println(acsResponse.getTaskInfo().getContent());
      String taskId = acsResponse.getTaskInfo().getNextTaskId();
      // 保证文件异步处理成功
      if (taskId != null) {
        Boolean isTrue = true;
        while (isTrue) {
          GetDDLJobStatusRequest getDDLJobStatusRequest = new GetDDLJobStatusRequest();
          // 任务的ID。
          getDDLJobStatusRequest.setTaskId(taskId);
          GetDDLJobStatusResponse jobAcsResponse = client.getAcsResponse(getDDLJobStatusRequest);
          // 正在进行的TaskID。如果为空，说明全部任务已经结束。
          System.out.println(jobAcsResponse.getData().getNextTaskId());
          if (jobAcsResponse.getData().getNextTaskId() != null) {
            taskId = jobAcsResponse.getData().getNextTaskId();
          } else {
            isTrue = false;
          }
        }
      }
      return true;
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return false;
  }

  /**
   * DataWorks OpenAPI : GetDDLJobStatus
   * Link : https://help.aliyun.com/document_detail/185659.html
   *
   * @param getDDLJobStatusDto
   */
  public GetDDLJobStatusResponse.Data getDDLJobStatus(GetDDLJobStatusDto getDDLJobStatusDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      GetDDLJobStatusRequest getDDLJobStatusRequest = new GetDDLJobStatusRequest();
      // 任务的ID。
      getDDLJobStatusRequest.setTaskId(getDDLJobStatusDto.getTaskId());
      GetDDLJobStatusResponse acsResponse = client.getAcsResponse(getDDLJobStatusRequest);
      // 任务的内容。
      System.out.println(acsResponse.getData().getContent());
      // 正在进行的TaskID。如果为空，说明全部任务已经结束。
      System.out.println(acsResponse.getData().getNextTaskId());
      // 任务的状态。
      System.out.println(acsResponse.getData().getStatus());
      // 任务的ID。
      System.out.println(acsResponse.getData().getTaskId());
      return acsResponse.getData();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

  /**
   * DataWorks OpenAPI : CreateTableLevel
   * Link : https://help.aliyun.com/document_detail/185669.html
   *
   * @param createTableLevelDto
   */
  public Long createTableLevel(CreateTableLevelDto createTableLevelDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      CreateTableLevelRequest createTableLevelRequest = new CreateTableLevelRequest();
      // 层级的类型，包括1（逻辑层级）和2（物理层级）。
      createTableLevelRequest.setLevelType(createTableLevelDto.getLevelType());
      // 层级的名称。
      createTableLevelRequest.setName(createTableLevelDto.getName());
      // DataWorks工作空间的ID。
      createTableLevelRequest.setProjectId(createTableLevelDto.getProjectId());
      // 层级的描述。
      createTableLevelRequest.setDescription(createTableLevelDto.getDescription());
      CreateTableLevelResponse acsResponse = client.getAcsResponse(createTableLevelRequest);
      // 层级ID。
      System.out.println(acsResponse.getLevelId());
      return acsResponse.getLevelId();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

  /**
   * DataWorks OpenAPI : ListTableLevel
   * Link :
   *
   * @param listTableLevelDto
   */
  public ListTableLevelResponse.TableLevelInfo listTableLevel(ListTableLevelDto listTableLevelDto) {
    try {
      IAcsClient client = dataWorksOpenApiClient.createClient();
      ListTableLevelRequest listTableLevelRequest = new ListTableLevelRequest();
      // 层级的类型，包括1（逻辑层级）和2（物理层级）。
      listTableLevelRequest.setLevelType(listTableLevelDto.getLevelType());
      // DataWorks工作空间的ID。您可以进入DataWorks管理控制台获取。
      listTableLevelRequest.setProjectId(listTableLevelDto.getProjectId());
      // 分页查询页码。默认为1。
      listTableLevelRequest.setPageNum(listTableLevelDto.getPageNum());
      // 每页显示的条数，默认为10条。
      listTableLevelRequest.setPageSize(listTableLevelDto.getPageSize());
      ListTableLevelResponse acsResponse = client.getAcsResponse(listTableLevelRequest);
      // 层级的总数。
      System.out.println(acsResponse.getTableLevelInfo().getTotalCount());
      for (ListTableLevelResponse.TableLevelInfo.LevelListItem levelListItem : acsResponse.getTableLevelInfo()
          .getLevelList()) {
        // 层级ID。
        System.out.println(levelListItem.getLevelId());
        // 层级的类型，包括1（逻辑层级）和2（物理层级）。
        System.out.println(levelListItem.getLevelType());
        // 层级的描述。
        System.out.println(levelListItem.getDescription());
        // 层级的名称。
        System.out.println(levelListItem.getName());
        // DataWorks工作空间的ID。
        System.out.println(levelListItem.getProjectId());
      }
      return acsResponse.getTableLevelInfo();
    } catch (ServerException e) {
      e.printStackTrace();
    } catch (ClientException e) {
      e.printStackTrace();
      // 请求ID
      System.out.println(e.getRequestId());
      // 错误码
      System.out.println(e.getErrCode());
      // 错误信息
      System.out.println(e.getErrMsg());
    }
    return null;
  }

}
