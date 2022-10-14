package com.aliyun.dataworks.demo;

import com.aliyun.dataworks.dto.*;
import com.aliyun.dataworks.services.MetaServiceProxy;
import com.aliyuncs.dataworks_public.model.v20200518.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;

/**
 * 演示如何通过DataWorks OpenAPI构建自定义元数据平台
 *
 * @author dataworks demo
 */
@RestController
@RequestMapping("/meta")
public class MetaRestController {

    @Autowired
    private MetaServiceProxy metaService;

    /**
     * 分页查询表数据
     *
     * @param listTablesDto
     * @return {@link GetMetaDBTableListResponse.Data}
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/listTables")
    public GetMetaDBTableListResponse.Data listMetaDBTable(ListTablesDto listTablesDto) {
        return metaService.getMetaDBTableList(listTablesDto);
    }

    /**
     * 获取表的基本属性
     *
     * @param getMetaTableBasicInfoDto
     * @return {@link GetMetaTableBasicInfoResponse.Data}
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getTable")
    public GetMetaTableBasicInfoResponse.Data getMetaTableBasicInfo(GetMetaTableBasicInfoDto getMetaTableBasicInfoDto) {
        return metaService.getMetaTableBasicInfo(getMetaTableBasicInfoDto);
    }

    /**
     * 查询表的列信息
     *
     * @param getMetaTableColumnDto
     * @return {@link GetMetaTableColumnResponse.Data}
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getTableColumn")
    public GetMetaTableColumnResponse.Data getMetaTableColumn(GetMetaTableColumnDto getMetaTableColumnDto) {
        return metaService.getMetaTableColumn(getMetaTableColumnDto);
    }

    /**
     * 查询表分区
     *
     * @param getMetaTablePartitionDto
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getTablePartition")
    public GetMetaTablePartitionResponse.Data getMetaTablePartition(GetMetaTablePartitionDto getMetaTablePartitionDto) {
        return metaService.getMetaTablePartition(getMetaTablePartitionDto);
    }

    /**
     * 查询表的血缘关系
     *
     * @param getMetaTableLineageDto
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getTableLineage")
    public GetMetaTableLineageResponse.Data getMetaTableLineage(GetMetaTableLineageDto getMetaTableLineageDto) {
        return metaService.getMetaTableLineage(getMetaTableLineageDto);
    }

    /**
     * 查询字段的血缘关系
     *
     * @param getMetaColumnLineageDto
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/getColumnLineage")
    public GetMetaColumnLineageResponse.Data getMetaColumnLineage(GetMetaColumnLineageDto getMetaColumnLineageDto) {
        return metaService.getMetaColumnLineage(getMetaColumnLineageDto);
    }

    /**
     * 创建表
     *
     * @param createTableDto
     * @return
     */
    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/createTable")
    public boolean createTable(CreateTableDto createTableDto) {
        return metaService.createTable(createTableDto);
    }

}
