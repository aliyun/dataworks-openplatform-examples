package com.aliyun.dataworks.dto;

import lombok.Data;

/**
 * @author dataworks demo
 * 参考文档https://help.aliyun.com/document_detail/173942.html?spm=a2c4g.11186623.0.0.549b6cf0blqGsm
 */
@Data
public class ListFilesDTO {

    /**
     * DataWorks工作空间的ID，必填
     */
    private Long projectId;

    /**
     * 文件名称的关键字。支持模糊匹配，即输入关键字即可查询包含该关键字的所有文件。
     */
    private String keyword;

    /**
     * 请求的数据页数，用于翻页，必填
     */
    private Integer pageNumber;

    /**
     * 每页显示的条数，默认为10条，最大为100条。必填
     */
    private Integer pageSize;

    /**
     * 文件所属的功能模块。取值如下：
     *
     * NORMAL：数据开发。
     * MANUAL：手动任务。
     * MANUAL_BIZ：手动业务流程。
     * SKIP：数据开发的空跑调度。
     * ADHOCQUERY：临时查询。
     * COMPONENT：组件管理。
     */
    private String useType;

    /**
     * 文件的代码类型。多个逗号分隔：10,23
     */
    private String fileTypes;

    /**
     * 文件责任人
     */
    private String owner;

    /**
     * 调度节点ID
     */
    private Long nodeId;

    /**
     * 文件路径
     */
    private String fileFolderPath;
}
