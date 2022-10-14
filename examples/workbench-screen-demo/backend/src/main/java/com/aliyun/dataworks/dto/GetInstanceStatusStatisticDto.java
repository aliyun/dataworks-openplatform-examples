package com.aliyun.dataworks.dto;

/**
 * https://help.aliyun.com/document_detail/212637.html
 * @author dataworks demo
 */
public class GetInstanceStatusStatisticDto {

  /**
   * DataWorks工作空间的ID。您可以登录DataWorks管控台，进入工作空间配置页面获取工作空间ID。
   */
  private Long projectId;

  /**
   *
   * 运行的环境，包括PROD（生产环境）和DEV（开发环境）。
   */
  private String projectEnv;

  /**
   * 获取不同实例状态数量的业务日期。
   */
  private String bizDate;

  /**
   * 调度的类型，取值如下：
   *
   * NORMAL：周期调度
   * MANUAL：手动调度
   * PAUSE：暂停调度
   * SKIP：空跑
   */
  private String schedulerType;

  /**
   * Dag Type的类型：
   *
   * MANUAL：手动任务工作流
   * SMOKE_TEST：冒烟测试工作流
   * SUPPLY_DATA：补数据
   * BUSINESS_PROCESS_DAG：一次性业务流程工作流
   */
  private String dagType;

  public Long getProjectId() {
    return projectId;
  }

  public void setProjectId(Long projectId) {
    this.projectId = projectId;
  }

  public String getProjectEnv() {
    return projectEnv;
  }

  public void setProjectEnv(String projectEnv) {
    this.projectEnv = projectEnv;
  }

  public String getBizDate() {
    return bizDate;
  }

  public void setBizDate(String bizDate) {
    this.bizDate = bizDate;
  }

  public String getSchedulerType() {
    return schedulerType;
  }

  public void setSchedulerType(String schedulerType) {
    this.schedulerType = schedulerType;
  }

  public String getDagType() {
    return dagType;
  }

  public void setDagType(String dagType) {
    this.dagType = dagType;
  }
}
