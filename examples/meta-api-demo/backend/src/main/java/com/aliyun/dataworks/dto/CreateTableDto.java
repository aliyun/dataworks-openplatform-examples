package com.aliyun.dataworks.dto;

import com.aliyuncs.dataworks_public.model.v20200518.CreateTableRequest;
import lombok.Data;

import java.util.List;

/**
 * @author dataworks demo
 *         详细字段说明参考文档 https://help.aliyun.com/document_detail/185656.html
 */
@Data
public class CreateTableDto {
    private String clientToken;
    private List<CreateTableRequest.Columns> columns;
    private Integer lifeCycle;
    private List<CreateTableRequest.Themes> themes;
    private Long logicalLevelId;
    private String endpoint;
    private Integer envType;
    private Integer hasPart;
    private String tableName;
    private String appGuid;
    private Long projectId;
    private Long categoryId;
    private Integer visibility;
    private Long physicsLevelId;
    private String ownerId;
    private Integer isView;
    private String externalTableType;
    private String location;
    private String comment;
}
