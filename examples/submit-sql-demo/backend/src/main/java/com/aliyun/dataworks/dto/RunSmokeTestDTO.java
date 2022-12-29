package com.aliyun.dataworks.dto;

/**
 * @Author guangzhen.zk
 * @create 2022/12/1 下午5:06
 */
public class RunSmokeTestDTO {

    /**
     * projectEnv 工作空间的环境 包括PROD（生产环境）和DEV（开发环境）
     */
    private String projectEnv = "DEV";

    /**
     * bizdate 业务日期 2020-05-26 00:00:00
     */
    private String bizdate;

    /**
     * nodeId 节点的ID
     */
    private Long nodeId;

    /**
     * nodeParams 节点的参数。配置为一个JSON字符串，Key为节点 ID，Value为参数实际取值
     */
    private String nodeParams;

    /**
     * 项目空间ID
     */
    private Long projectId;

    /**
     *
     */
    private String name;

    public String getProjectEnv() {
        return projectEnv;
    }

    public void setProjectEnv(String projectEnv) {
        this.projectEnv = projectEnv;
    }

    public String getBizdate() {
        return bizdate;
    }

    public void setBizdate(String bizdate) {
        this.bizdate = bizdate;
    }

    public Long getNodeId() {
        return nodeId;
    }

    public void setNodeId(Long nodeId) {
        this.nodeId = nodeId;
    }

    public String getNodeParams() {
        return nodeParams;
    }

    public void setNodeParams(String nodeParams) {
        this.nodeParams = nodeParams;
    }

    public Long getProjectId() {
        return projectId;
    }

    public void setProjectId(Long projectId) {
        this.projectId = projectId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
