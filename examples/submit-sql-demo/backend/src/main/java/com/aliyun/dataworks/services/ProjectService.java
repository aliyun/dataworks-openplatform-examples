package com.aliyun.dataworks.services;

import com.aliyuncs.dataworks_public.model.v20200518.ListProjectsRequest;
import com.aliyuncs.dataworks_public.model.v20200518.ListProjectsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author guangzhen.zk
 * @create 2022/12/1 下午4:04
 */
@Service
public class ProjectService {

    @Autowired
    private DataWorksOpenApiClient dataWorksOpenApiClient;

    /**
     * https://help.aliyun.com/document_detail/178393.html?spm=a2c4g.11186623.0.0.6d016cf0DG5vGJ
     * @param pageNumber
     * @param pageSize
     * @return
     */
    public ListProjectsResponse.PageResult listProjects(Integer pageNumber, Integer pageSize) {
        try {
            ListProjectsRequest listProjectsRequest = new ListProjectsRequest();
            listProjectsRequest.setPageNumber(pageNumber);
            listProjectsRequest.setPageSize(pageSize);
            ListProjectsResponse listProjectsResponse = dataWorksOpenApiClient.createClient().getAcsResponse(listProjectsRequest);
            System.out.println(listProjectsResponse.getRequestId());
            return listProjectsResponse.getPageResult();
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
