package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 参考文档：https://help.aliyun.com/document_detail/173937.html
 */
@Data
public class CreateFileDTO {

    /**
     * 文件的路径
     */
    private String fileFolderPath;

    /**
     * 项目空间ID
     */
    private Long projectId;

    /**
     * 文件的名称
     */
    private String fileName;

    /**
     * 文件描述
     */
    private String fileDescription;

    /**
     * 文件的代码类型
     */
    private Integer fileType;

    /**
     * 文件责任人的阿里云用户ID。如果该参数为空，则默认使用调用者的阿里云用户ID。
     */
    private String owner;

    /**
     * 文件代码内容
     */
    private String content;

    /**
     * AutoRerunTimes
     */
    private Integer autoRerunTimes;

    /**
     * 出错自动重跑时间间隔，单位为毫秒。最大为1800000毫秒（30分钟）。
     */
    private Integer autoRerunIntervalMillis;

    /**
     * 重跑属性。取值如下：
     * <p>
     * ALL_ALLOWED：运行成功或失败后皆可重跑。
     * FAILURE_ALLOWED：运行成功后不可重跑，运行失败后可以重跑。
     * ALL_DENIED：运行成功或失败皆不可重跑。
     */
    private String rerunMode;

    /**
     * 是否暂停调度，取值如下：
     * <p>
     * true：暂停调度。
     * false：不暂停调度。
     */
    private Boolean stop = false;

    /**
     * 调度参数。
     */
    private String paraValue;

    /**
     * 开始自动调度的毫秒时间戳。
     */
    private Long startEffectDate;

    /**
     * 停止自动调度的时间戳，单位为毫秒。
     */
    private Long endEffectDate;

    /**
     * 周期调度的cron表达式
     */
    private String cronExpress;

    /**
     * 调度周期的类型
     */
    private String cycleType;

    /**
     * 依赖上一周期的方式。取值如下：
     * <p>
     * SELF：依赖项选择本节点。
     * CHILD：依赖项选择一级子节点。
     * USER_DEFINE：依赖项选择其他节点。
     * NONE：未选择依赖项，即不会依赖上一周期。
     */
    private String dependentType;

    /**
     * 依赖上一周期的节点列表。
     */
    private String dependentNodeIdList;

    /**
     * 文件依赖的上游文件的输出名称，多个输出使用英文逗号（,）分隔。
     */
    private String inputList;

    /**
     * 文件发布成任务后，任务执行时对应的资源组
     */
    private String resourceGroupIdentifier;

    /**
     * 文件发布成任务后，任务执行时连接的数据源。
     */
    private String connectionName;

    /**
     * 文件是否开启自动解析功能。取值如下：
     * <p>
     * true：文件会自动解析代码。
     * false：文件不会自动解析代码。
     */
    private Boolean autoParsing = false;

    /**
     * 调度的类型，取值如下：
     * <p>
     * NORMAL：正常调度任务。
     * MANUAL：手动任务，不会被日常调度，对应手动业务流程下的节点。
     * PAUSE：暂停任务。
     * SKIP：空跑任务，被日常调度，但启动调度时直接被置为成功。
     */
    private String schedulerType;

    /**
     * 任务的高级配置
     */
    private String advancedSettings;

    /**
     * 发布后是否立即启动。
     */
    private Boolean startImmediately;

    /**
     * 节点的上下文输入参数
     */
    private String inputParameters;

    /**
     * 节点的上下文输出参数
     */
    private String outputParameters;
}
